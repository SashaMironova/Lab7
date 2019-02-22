package Client;

import java.awt.*;
import java.lang.reflect.Type;
import java.net.*;
import java.io.*;
import java.util.*;

import Server.InjuredPoliceman;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;

public class Client{

//    public void paintComponent(Graphics g){
//        super.paintComponent(g);
//        g.setColor(Color.RED);
//        g.drawRect(400,10,30,40);
//    }

    public static ArrayList<InjuredPoliceman> injuredPolicemen;

    public static void main(String[] args) {
        int serverPort = 1235;
        String address = "127.0.0.1";

//      GridLayout gridLayout = new GridLayout(1,2);
//        frame.setLayout(gridLayout);
//        GUIClient s = new GUIClient();
//        s.setPreferredSize(new Dimension(200,600));
//        c.setBackground(Color.WHITE);
//        s.setBackground(Color.BLUE);
//        //s.addComponents();
//        //s.addComponents();
//        frame.add(c);
//        frame.add(s);

        try {
            InetAddress ipAddress = InetAddress.getByName(address);
            Socket socket = new Socket(ipAddress, serverPort);
            System.out.println("Соединение установлено\nВведите одну из команд в следующих форматах:" +
                    "\nremove_lower номер_элемента, remove id_элемента, clear, add имя год_рождения местонахождение " +
                    "состояние раненая_часть_лица раненая_часть_тела цвет, get_collection, select раненая_часть_лица, exit");

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            DataInputStream in = new DataInputStream(inputStream);
            DataOutputStream out = new DataOutputStream(outputStream);//todo если коллекция пуста, вызвана команда гет коллекшн, что происходит

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String enterStr = null;


            Gson gson = new Gson();
            String str; // переменная для хранения того, что прислал сервер

            Type type = new TypeToken<ArrayList<InjuredPoliceman>>() {
            }.getType();

            str = in.readUTF();
            injuredPolicemen = gson.fromJson(str, type);

            JFrame frame = new JFrame("Клиент");
            GridLayout gridLayout = new GridLayout(1,2);
            frame.setResizable(false);
            frame.setLayout(gridLayout);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
            frame.setSize(800,600);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            frame.setLocation(dim.width/3*2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
            frame.add(new Display());
            frame.add(new Filter());//todo убрать лишнее, оставить передачу коллекции

            while (true) {
                enterStr = reader.readLine(); // ждем пока пользователь введет что-то и нажмет кнопку Enter.
                if (enterStr.equals("exit")){
                    out.close();
                    in.close();
                    socket.close();
                    reader.close();
                    System.exit(0);
                    break;
                }

                out.writeUTF(enterStr);
                str = in.readUTF();
                if (str.equals("OK") || str.equals("Полицейских с такой раненой частью лица нет") || str.equals("Неправильная команда")) {
                    System.out.println(str);
                }
                else{
                    injuredPolicemen = gson.fromJson(str, type);
                    for (int i = 0; i < injuredPolicemen.size(); i++) {
                        System.out.println("Я не баг, я фича! Привет, Земляне!");
                        System.out.println("Раненого полицейского зовут: " + injuredPolicemen.get(i).name + ", он родился в " + injuredPolicemen.get(i).yearOfBirth
                                + " году, он находится " + injuredPolicemen.get(i).location + ", его создали " + injuredPolicemen.get(i).dateOfCreation + ", он " + injuredPolicemen.get(i).colour + " цвета");
                    }
                }
                out.flush(); // заставляем поток закончить передачу данных.
            }
        } catch (Exception x) {
            System.out.println("Сервер недоступен");
        }
    }
}
