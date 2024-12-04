import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GUI_main {
    static Scheduler scheduler;
    static List<Process> barProcesses = new ArrayList<>();
    static List<Process> statProcesses = new ArrayList<>();
    public static void main(String[] args) {
        // Scanner input = new Scanner(System.in);
        List<Process> processes = new ArrayList<>();
        List<Color> colors = new ArrayList<>();
        colors.add(new Color(80));
        colors.add(new Color(60));
        colors.add(new Color(100));
        colors.add(new Color(70));
        colors.add(new Color(110));
        colors.add(new Color(50));
        colors.add(new Color(180));
        colors.add(new Color(90));
        colors.add(new Color(130));
        colors.add(new Color(140));
        colors.add(new Color(120));
        colors.add(new Color(40));
        colors.add(new Color(170));
        colors.add(new Color(150));
        colors.add(new Color(160));
        // int numberOfProcesses;
        // String name;
        // int burstTime;
        // int arrivalTime;
        // int priority;
        // int quantum;

        // Create the main frame
        JFrame frame = new JFrame("Menu Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 650);

        // Create the CardLayout
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        // Create the Welcome Menu panel with FlowLayout
        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20)); // Centered, with gaps

        JLabel mainLabel1 = new JLabel("Operating systems CPU Schedulers Assignment");
        mainLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        mainLabel1.setFont(new Font("Arial", Font.BOLD, 17));
        JLabel mainLabel2 = new JLabel("Please select a scheduling algorithm:");
        mainLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        mainLabel2.setFont(new Font("Arial", Font.BOLD, 15));
        JLabel mainLabel3 = new JLabel("Developed with passion by:");
        mainLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        mainLabel3.setFont(new Font("Arial", Font.BOLD, 15));

        JLabel mainLabelDev1 = new JLabel("1. Mahmoud Khaled: 20220317");
        mainLabelDev1.setHorizontalAlignment(SwingConstants.CENTER);
        mainLabelDev1.setFont(new Font("Arial", Font.BOLD, 13));
        JLabel mainLabelDev2 = new JLabel("2. Philopater Karam: 20220246");
        mainLabelDev2.setHorizontalAlignment(SwingConstants.CENTER);
        mainLabelDev2.setFont(new Font("Arial", Font.BOLD, 13));
        JLabel mainLabelDev3 = new JLabel("3. Mahmoud Farag: 20220???  ");
        mainLabelDev3.setHorizontalAlignment(SwingConstants.CENTER);
        mainLabelDev3.setFont(new Font("Arial", Font.BOLD, 13));
        JLabel mainLabelDev4 = new JLabel("4. Kirolos Adel: 20220???        ");
        mainLabelDev4.setHorizontalAlignment(SwingConstants.CENTER);
        mainLabelDev4.setFont(new Font("Arial", Font.BOLD, 13));
        JLabel mainLabelDev5 = new JLabel("5. Jonathan Mokhles: 20220???");
        mainLabelDev5.setHorizontalAlignment(SwingConstants.CENTER);
        mainLabelDev5.setFont(new Font("Arial", Font.BOLD, 13));

        JButton sjfButton = new JButton("Shortest Job First");
        JButton pjfButton = new JButton("Priority scheduling");
        JButton srtfButton = new JButton("Shortest remaining time");
        JButton fcaiButton = new JButton("FCAI Scheduling");

        // sjfButton.setFont(new Font());
        // sjfButton.setFont(new Font("Arial", Font.PLAIN, 12));
        // sjfButton.setFont(new Font("Arial", Font.PLAIN, 12));
        // sjfButton.setFont(new Font("Arial", Font.PLAIN, 12));

        // Set smaller button sizes
        Dimension buttonSize = new Dimension(150, 40);
        sjfButton.setPreferredSize(buttonSize);
        pjfButton.setPreferredSize(buttonSize);
        srtfButton.setPreferredSize(buttonSize);
        fcaiButton.setPreferredSize(buttonSize);

        // Add action listeners to buttons
        sjfButton.addActionListener(e -> cardLayout.show(mainPanel, "sjf"));
        pjfButton.addActionListener(e -> cardLayout.show(mainPanel, "pjf"));
        srtfButton.addActionListener(e -> cardLayout.show(mainPanel, "srtf"));
        fcaiButton.addActionListener(e -> cardLayout.show(mainPanel, "fcai"));

        
        // Add components to the menu panel
        menuPanel.add(Box.createRigidArea(new Dimension(800, 40)));
        menuPanel.add(mainLabel1);
        menuPanel.add(Box.createRigidArea(new Dimension(800, 0)));
        menuPanel.add(mainLabel2);
        menuPanel.add(Box.createRigidArea(new Dimension(800, 0))); // Forces a new line (width > panel width)
        menuPanel.add(sjfButton);
        // menuPanel.add(Box.createRigidArea(new Dimension(800, 0))); // Forces a new line (width > panel width)
        menuPanel.add(pjfButton);
        menuPanel.add(Box.createRigidArea(new Dimension(800, 0))); // Forces a new line (width > panel width)
        menuPanel.add(srtfButton);
        // menuPanel.add(Box.createRigidArea(new Dimension(800, 0))); // Forces a new line (width > panel width)
        menuPanel.add(fcaiButton);
        menuPanel.add(Box.createRigidArea(new Dimension(800, 0))); // Forces a new line (width > panel width)
        menuPanel.add(mainLabel3);
        menuPanel.add(Box.createRigidArea(new Dimension(800, 0))); // Forces a new line (width > panel width)
        menuPanel.add(mainLabelDev1);
        menuPanel.add(mainLabelDev2);
        menuPanel.add(mainLabelDev3);
        menuPanel.add(mainLabelDev4);
        menuPanel.add(mainLabelDev5);

        

        // Create the Shortest Job panel #################################################################
        JPanel sjfPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        // sjfPanel.setBackground(Color.CYAN);

        JLabel sjflabel1 = new JLabel("Welcome to Shortest Job First Scheduling");
        sjflabel1.setFont(new Font("Arial", Font.BOLD, 17));

        JLabel sjflabel2 = new JLabel("Please enter add your process:");
        sjflabel2.setFont(new Font("Arial", Font.BOLD, 16));


        JLabel sjflabel3 = new JLabel("Name:");
        sjflabel3.setFont(new Font("Arial", Font.BOLD, 13));
        JLabel sjflabel4 = new JLabel("Arival time:");
        sjflabel4.setFont(new Font("Arial", Font.BOLD, 13));
        JLabel sjflabel5 = new JLabel("Burst time:");
        sjflabel5.setFont(new Font("Arial", Font.BOLD, 13));
        JTextField sjfname = new JTextField(10);  
        JTextField sjfarival = new JTextField(10); 
        JTextField sjfburst = new JTextField(10); 

        JButton sjfstart = new JButton("Start Scheduling!");
        JButton sjfadd = new JButton("Add process");
        JButton sjfback = new JButton("Back");
        sjfstart.setPreferredSize(buttonSize);
        sjfadd.setPreferredSize(buttonSize);
        sjfback.setPreferredSize(buttonSize);
        sjfstart.addActionListener( e -> {

            if(processes.isEmpty()) {
                JOptionPane.showMessageDialog(
                    null,                          
                    "Please add at least one procces",     
                    "Error",                       
                    JOptionPane.ERROR_MESSAGE      
                );

            } else {

                
                // scheduler = new ShortestJobFirstScheduler();
                // scheduler.schedule(processes);
                
                // barProcesses = scheduler.getBarProcesses();
                // barProcesses = scheduler.getStatProcesses();
                
                JOptionPane.showMessageDialog(
                    null,                      
                    "Process sent to shortest job first schedular",
                    "Done",                       
                    JOptionPane.INFORMATION_MESSAGE
                );
            }

        });
        sjfadd.addActionListener(e -> {

            if(sjfname.getText().isEmpty() || sjfarival.getText().isEmpty() || sjfburst.getText().isEmpty()) {
                JOptionPane.showMessageDialog(
                    null,                          // Parent component (null means it will be centered on the screen)
                    "Please fill all fields",     // The error message
                    "Error",                       // Title of the dialog
                    JOptionPane.ERROR_MESSAGE      // Type of message (ERROR_MESSAGE indicates an error)
                );

            } else {

                int i = 0;
                processes.clear();
    
                String sjfnameString = sjfname.getText();
                int sjfarivaltime = Integer.parseInt(sjfarival.getText());
                int sjfbursttime = Integer.parseInt(sjfburst.getText());
    
                sjfname.setText("");
                sjfarival.setText("");
                sjfburst.setText("");
                // System.out.println(sjfnameString);
                // System.out.println(sjfarivaltime);
                // System.out.println(sjfbursttime);
    
                Process proc = new Process(sjfnameString, sjfarivaltime, sjfbursttime, colors.get(i++));
                processes.add(proc);
    
                // for (Process process : processes) {
                //     System.out.println(process.name);
                //     System.out.println(process.arrivalTime);
                //     System.out.println(process.burstTime);
                // }

                JOptionPane.showMessageDialog(
                    null,                      
                    "Process added",
                    "Done",                       
                    JOptionPane.INFORMATION_MESSAGE
                );
              
            }



        });
        sjfback.addActionListener(e -> {
            processes.clear();
            cardLayout.show(mainPanel, "menu");

        });

        
        sjfPanel.add(Box.createRigidArea(new Dimension(800, 40)));
        sjfPanel.add(sjflabel1);
        sjfPanel.add(Box.createRigidArea(new Dimension(800, 0)));
        sjfPanel.add(sjflabel2);
        sjfPanel.add(Box.createRigidArea(new Dimension(800, 0)));
        sjfPanel.add(sjflabel3);
        sjfPanel.add(sjfname);
        sjfPanel.add(Box.createRigidArea(new Dimension(800, 0)));
        sjfPanel.add(sjflabel4);
        sjfPanel.add(sjfarival);
        sjfPanel.add(Box.createRigidArea(new Dimension(800, 0)));
        sjfPanel.add(sjflabel5);
        sjfPanel.add(sjfburst);
        sjfPanel.add(Box.createRigidArea(new Dimension(800, 0)));
        sjfPanel.add(sjfstart);
        sjfPanel.add(sjfadd);
        sjfPanel.add(Box.createRigidArea(new Dimension(800, 0)));
        sjfPanel.add(sjfback);


        // Create the Priority panel #################################################################

        JPanel pjfPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        // pjfPanel.setBackground(Color.CYAN);

        JLabel pjflabel1 = new JLabel("Welcome to Priority Scheduling");
        pjflabel1.setFont(new Font("Arial", Font.BOLD, 17));

        JLabel pjflabel2 = new JLabel("Please enter add your process:");
        pjflabel2.setFont(new Font("Arial", Font.BOLD, 16));


        JLabel pjflabel3 = new JLabel("Name:");
        pjflabel3.setFont(new Font("Arial", Font.BOLD, 13));
        JLabel pjflabel4 = new JLabel("Arival time:");
        pjflabel4.setFont(new Font("Arial", Font.BOLD, 13));
        JLabel pjflabel5 = new JLabel("Burst time:");
        pjflabel5.setFont(new Font("Arial", Font.BOLD, 13));
        JLabel pjflabel6 = new JLabel("Process priority:");
        pjflabel6.setFont(new Font("Arial", Font.BOLD, 13));
        JLabel pjflabel7 = new JLabel("Context switching:");
        pjflabel7.setFont(new Font("Arial", Font.BOLD, 13));
        JTextField pjfname = new JTextField(10);  
        JTextField pjfarival = new JTextField(10); 
        JTextField pjfburst = new JTextField(10); 
        JTextField pjfpriority = new JTextField(10); 
        JTextField pjfcontext = new JTextField(10); 

        JButton pjfstart = new JButton("Start Scheduling!");
        JButton pjfadd = new JButton("Add process");
        JButton pjfback = new JButton("Back");
        pjfstart.setPreferredSize(buttonSize);
        pjfadd.setPreferredSize(buttonSize);
        pjfback.setPreferredSize(buttonSize);
        pjfstart.addActionListener( e -> {

            if(processes.isEmpty()) {
                JOptionPane.showMessageDialog(
                    null,                         
                    "Please add at least one procces",
                    "Error",                       
                    JOptionPane.ERROR_MESSAGE      
                );

            } else {

                
                // scheduler = new PriorityScheduler();
                // scheduler.schedule(processes);
                
                // barProcesses = scheduler.getBarProcesses();
                // barProcesses = scheduler.getStatProcesses();
                
                JOptionPane.showMessageDialog(
                    null,                      
                    "Process sent to priority schedular",
                    "Done",                       
                    JOptionPane.INFORMATION_MESSAGE
                );
            }

        });
        pjfadd.addActionListener(e -> {

            if(pjfname.getText().isEmpty() || pjfarival.getText().isEmpty() || pjfburst.getText().isEmpty() || pjfpriority.getText().isEmpty() || pjfcontext.getText().isEmpty()) {
                JOptionPane.showMessageDialog(
                    null,                 
                    "Please fill all fields",  
                    "Error",                    
                    JOptionPane.ERROR_MESSAGE    
                );

            } else {
                int i = 0;
                processes.clear();

                String pjfnameString = pjfname.getText();
                int pjfarivaltime = Integer.parseInt(pjfarival.getText());
                int pjfbursttime = Integer.parseInt(pjfburst.getText());
                int pjfpriorityInput = Integer.parseInt(pjfpriority.getText());
                int pjfcontextInput = Integer.parseInt(pjfcontext.getText());

                pjfname.setText("");
                pjfarival.setText("");
                pjfburst.setText("");
                pjfpriority.setText("");
                pjfcontext.setText("");
                // System.out.println(pjfnameString);
                // System.out.println(pjfarivaltime);
                // System.out.println(pjfbursttime);

                Process proc = new Process(pjfnameString, pjfarivaltime, pjfbursttime, pjfpriorityInput, colors.get(i++));
                processes.add(proc);

                // for (Process process : processes) {
                //     System.out.println(process.name);
                //     System.out.println(process.arrivalTime);
                //     System.out.println(process.burstTime);
                // }

                JOptionPane.showMessageDialog(
                    null,                      
                    "Process added",
                    "Done",                       
                    JOptionPane.INFORMATION_MESSAGE
                );

            }
        });
        pjfback.addActionListener(e -> {
            processes.clear();
            cardLayout.show(mainPanel, "menu");

        });

        
        pjfPanel.add(Box.createRigidArea(new Dimension(800, 5)));
        pjfPanel.add(pjflabel1);
        pjfPanel.add(Box.createRigidArea(new Dimension(800, 0)));
        pjfPanel.add(pjflabel2);
        pjfPanel.add(Box.createRigidArea(new Dimension(800, 0)));
        pjfPanel.add(pjflabel3);
        pjfPanel.add(pjfname);
        pjfPanel.add(Box.createRigidArea(new Dimension(800, 0)));
        pjfPanel.add(pjflabel4);
        pjfPanel.add(pjfarival);
        pjfPanel.add(Box.createRigidArea(new Dimension(800, 0)));
        pjfPanel.add(pjflabel5);
        pjfPanel.add(pjfburst);
        pjfPanel.add(Box.createRigidArea(new Dimension(800, 0)));
        pjfPanel.add(pjflabel6);
        pjfPanel.add(pjfpriority);
        pjfPanel.add(Box.createRigidArea(new Dimension(800, 0)));
        pjfPanel.add(pjflabel7);
        pjfPanel.add(pjfcontext);
        pjfPanel.add(Box.createRigidArea(new Dimension(800, 0)));
        pjfPanel.add(pjfstart);
        pjfPanel.add(pjfadd);
        pjfPanel.add(Box.createRigidArea(new Dimension(800, 0)));
        pjfPanel.add(pjfback);




        // Create the Shortest remaining time job first panel #################################################################

        JPanel srtfPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        // srtfPanel.setBackground(Color.CYAN);

        JLabel srtflabel1 = new JLabel("Welcome to Shortest Remaining Time Scheduling");
        srtflabel1.setFont(new Font("Arial", Font.BOLD, 17));

        JLabel srtflabel2 = new JLabel("Please enter add your process:");
        srtflabel2.setFont(new Font("Arial", Font.BOLD, 16));


        JLabel srtflabel3 = new JLabel("Name:");
        srtflabel3.setFont(new Font("Arial", Font.BOLD, 13));
        JLabel srtflabel4 = new JLabel("Arival time:");
        srtflabel4.setFont(new Font("Arial", Font.BOLD, 13));
        JLabel srtflabel5 = new JLabel("Burst time:");
        srtflabel5.setFont(new Font("Arial", Font.BOLD, 13));
        JLabel srtflabel6 = new JLabel("Process priority:");
        srtflabel6.setFont(new Font("Arial", Font.BOLD, 13));
        JLabel srtflabel7 = new JLabel("Context switching:");
        srtflabel7.setFont(new Font("Arial", Font.BOLD, 13));
        JTextField srtfname = new JTextField(10);  
        JTextField srtfarival = new JTextField(10); 
        JTextField srtfburst = new JTextField(10); 
        JTextField srtfpriority = new JTextField(10); 
        JTextField srtfcontext = new JTextField(10); 

        JButton srtfstart = new JButton("Start Scheduling!");
        JButton srtfadd = new JButton("Add process");
        JButton srtfback = new JButton("Back");
        srtfstart.setPreferredSize(buttonSize);
        srtfadd.setPreferredSize(buttonSize);
        srtfback.setPreferredSize(buttonSize);
        srtfstart.addActionListener( e -> {

            if(processes.isEmpty()) {
                JOptionPane.showMessageDialog(
                    null,                         
                    "Please add at least one procces",
                    "Error",                       
                    JOptionPane.ERROR_MESSAGE      
                );

            } else {

                
                // scheduler = new ShortestRemainingTimeFirstScheduler();
                // scheduler.schedule(processes);
                
                // barProcesses = scheduler.getBarProcesses();
                // barProcesses = scheduler.getStatProcesses();
                
                JOptionPane.showMessageDialog(
                    null,                      
                    "Process sent to shortest remaining time first schedular",
                    "Done",                       
                    JOptionPane.INFORMATION_MESSAGE
                );
            }

        });
        srtfadd.addActionListener(e -> {

            if(srtfname.getText().isEmpty() || srtfarival.getText().isEmpty() || srtfburst.getText().isEmpty() || srtfpriority.getText().isEmpty() || srtfcontext.getText().isEmpty()) {
                JOptionPane.showMessageDialog(
                    null,                 
                    "Please fill all fields",  
                    "Error",                    
                    JOptionPane.ERROR_MESSAGE    
                );

            } else {
                int i = 0;
                processes.clear();

                String srtfnameString = srtfname.getText();
                int srtfarivaltime = Integer.parseInt(srtfarival.getText());
                int srtfbursttime = Integer.parseInt(srtfburst.getText());
                int srtfpriorityInput = Integer.parseInt(srtfpriority.getText());
                int srtfcontextInput = Integer.parseInt(srtfcontext.getText());

                srtfname.setText("");
                srtfarival.setText("");
                srtfburst.setText("");
                srtfpriority.setText("");
                srtfcontext.setText("");
                // System.out.println(srtfnameString);
                // System.out.println(srtfarivaltime);
                // System.out.println(srtfbursttime);

                Process proc = new Process(srtfnameString, srtfarivaltime, srtfbursttime, srtfpriorityInput, colors.get(i++));
                processes.add(proc);

                // for (Process process : processes) {
                //     System.out.println(process.name);
                //     System.out.println(process.arrivalTime);
                //     System.out.println(process.burstTime);
                // }

                JOptionPane.showMessageDialog(
                    null,                      
                    "Process added",
                    "Done",                       
                    JOptionPane.INFORMATION_MESSAGE
                );

            }
        });
        srtfback.addActionListener(e -> {
            processes.clear();
            cardLayout.show(mainPanel, "menu");

        });

        
        srtfPanel.add(Box.createRigidArea(new Dimension(800, 5)));
        srtfPanel.add(srtflabel1);
        srtfPanel.add(Box.createRigidArea(new Dimension(800, 0)));
        srtfPanel.add(srtflabel2);
        srtfPanel.add(Box.createRigidArea(new Dimension(800, 0)));
        srtfPanel.add(srtflabel3);
        srtfPanel.add(srtfname);
        srtfPanel.add(Box.createRigidArea(new Dimension(800, 0)));
        srtfPanel.add(srtflabel4);
        srtfPanel.add(srtfarival);
        srtfPanel.add(Box.createRigidArea(new Dimension(800, 0)));
        srtfPanel.add(srtflabel5);
        srtfPanel.add(srtfburst);
        srtfPanel.add(Box.createRigidArea(new Dimension(800, 0)));
        srtfPanel.add(srtflabel6);
        srtfPanel.add(srtfpriority);
        srtfPanel.add(Box.createRigidArea(new Dimension(800, 0)));
        srtfPanel.add(srtflabel7);
        srtfPanel.add(srtfcontext);
        srtfPanel.add(Box.createRigidArea(new Dimension(800, 0)));
        srtfPanel.add(srtfstart);
        srtfPanel.add(srtfadd);
        srtfPanel.add(Box.createRigidArea(new Dimension(800, 0)));
        srtfPanel.add(srtfback);




        // Create the FCAI panel #################################################################

        JPanel fcaiPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        // fcaiPanel.setBackground(Color.CYAN);

        JLabel fcailabel1 = new JLabel("Welcome to FCAI Scheduling");
        fcailabel1.setFont(new Font("Arial", Font.BOLD, 17));

        JLabel fcailabel2 = new JLabel("Please enter add your process:");
        fcailabel2.setFont(new Font("Arial", Font.BOLD, 16));


        JLabel fcailabel3 = new JLabel("Name:");
        fcailabel3.setFont(new Font("Arial", Font.BOLD, 13));
        JLabel fcailabel4 = new JLabel("Arival time:");
        fcailabel4.setFont(new Font("Arial", Font.BOLD, 13));
        JLabel fcailabel5 = new JLabel("Burst time:");
        fcailabel5.setFont(new Font("Arial", Font.BOLD, 13));
        JLabel fcailabel6 = new JLabel("Process priority:");
        fcailabel6.setFont(new Font("Arial", Font.BOLD, 13));
        JLabel fcailabel7 = new JLabel("Time quantum:");
        fcailabel7.setFont(new Font("Arial", Font.BOLD, 13));
        JTextField fcainame = new JTextField(10);  
        JTextField fcaiarival = new JTextField(10); 
        JTextField fcaiburst = new JTextField(10); 
        JTextField fcaipriority = new JTextField(10); 
        JTextField fcaiquantum = new JTextField(10); 

        JButton fcaistart = new JButton("Start Scheduling!");
        JButton fcaiadd = new JButton("Add process");
        JButton fcaiback = new JButton("Back");
        fcaistart.setPreferredSize(buttonSize);
        fcaiadd.setPreferredSize(buttonSize);
        fcaiback.setPreferredSize(buttonSize);
        fcaistart.addActionListener( e -> {

            if(processes.isEmpty()) {
                JOptionPane.showMessageDialog(
                    null,                         
                    "Please add at least one procces",
                    "Error",                       
                    JOptionPane.ERROR_MESSAGE      
                );

            } else {

                
                // scheduler = new FCAIScheduler();
                // scheduler.schedule(processes);
                
                // barProcesses = scheduler.getBarProcesses();
                // barProcesses = scheduler.getStatProcesses();
                
                JOptionPane.showMessageDialog(
                    null,                      
                    "Process sent to FCAI schedular",
                    "Done",                       
                    JOptionPane.INFORMATION_MESSAGE
                );
            }

        });
        fcaiadd.addActionListener(e -> {

            if(fcainame.getText().isEmpty() || fcaiarival.getText().isEmpty() || fcaiburst.getText().isEmpty() || fcaipriority.getText().isEmpty() || fcaiquantum.getText().isEmpty()) {
                JOptionPane.showMessageDialog(
                    null,                 
                    "Please fill all fields",  
                    "Error",                    
                    JOptionPane.ERROR_MESSAGE    
                );

            } else {
                int i = 0;
                processes.clear();

                String fcainameString = fcainame.getText();
                int fcaiarivaltime = Integer.parseInt(fcaiarival.getText());
                int fcaibursttime = Integer.parseInt(fcaiburst.getText());
                int fcaipriorityInput = Integer.parseInt(fcaipriority.getText());
                int fcaiquantumInput = Integer.parseInt(fcaiquantum.getText());

                fcainame.setText("");
                fcaiarival.setText("");
                fcaiburst.setText("");
                fcaipriority.setText("");
                fcaiquantum.setText("");
                // System.out.println(fcainameString);
                // System.out.println(fcaiarivaltime);
                // System.out.println(fcaibursttime);

                Process proc = new Process(fcainameString, fcaiarivaltime, fcaibursttime, fcaipriorityInput, colors.get(i++));
                processes.add(proc);

                // for (Process process : processes) {
                //     System.out.println(process.name);
                //     System.out.println(process.arrivalTime);
                //     System.out.println(process.burstTime);
                // }

                JOptionPane.showMessageDialog(
                    null,                      
                    "Process added",
                    "Done",                       
                    JOptionPane.INFORMATION_MESSAGE
                );

            }
        });
        fcaiback.addActionListener(e -> {
            processes.clear();
            cardLayout.show(mainPanel, "menu");

        });

        
        fcaiPanel.add(Box.createRigidArea(new Dimension(800, 5)));
        fcaiPanel.add(fcailabel1);
        fcaiPanel.add(Box.createRigidArea(new Dimension(800, 0)));
        fcaiPanel.add(fcailabel2);
        fcaiPanel.add(Box.createRigidArea(new Dimension(800, 0)));
        fcaiPanel.add(fcailabel3);
        fcaiPanel.add(fcainame);
        fcaiPanel.add(Box.createRigidArea(new Dimension(800, 0)));
        fcaiPanel.add(fcailabel4);
        fcaiPanel.add(fcaiarival);
        fcaiPanel.add(Box.createRigidArea(new Dimension(800, 0)));
        fcaiPanel.add(fcailabel5);
        fcaiPanel.add(fcaiburst);
        fcaiPanel.add(Box.createRigidArea(new Dimension(800, 0)));
        fcaiPanel.add(fcailabel6);
        fcaiPanel.add(fcaipriority);
        fcaiPanel.add(Box.createRigidArea(new Dimension(800, 0)));
        fcaiPanel.add(fcailabel7);
        fcaiPanel.add(fcaiquantum);
        fcaiPanel.add(Box.createRigidArea(new Dimension(800, 0)));
        fcaiPanel.add(fcaistart);
        fcaiPanel.add(fcaiadd);
        fcaiPanel.add(Box.createRigidArea(new Dimension(800, 0)));
        fcaiPanel.add(fcaiback);


        // Add panels to the CardLayout
        mainPanel.add(menuPanel, "menu");
        mainPanel.add(sjfPanel, "sjf");
        mainPanel.add(pjfPanel, "pjf");
        mainPanel.add(srtfPanel, "srtf");
        mainPanel.add(fcaiPanel, "fcai");

        // Add main panel to frame
        frame.add(mainPanel);

        // Show the frame
        frame.setVisible(true);

           
    }
}
