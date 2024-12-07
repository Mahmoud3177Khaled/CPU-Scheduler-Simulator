import java.awt.*;
import java.util.ArrayList;
import java.util.List;
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
    static FCAIScheduler FCAIscheduler;
    static List<processPeriod> barProcesses = new ArrayList<>();
    static List<Process> statProcesses = new ArrayList<>();
    static int colorSelector = 0;

    static JPanel coloredPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));

    public static void main(String[] args) {
        // Scanner input = new Scanner(System.in);
        List<Process> processes = new ArrayList<>();
        List<Color> colors = new ArrayList<>();
        colors.add(new Color(142,69,133));
        colors.add(new Color(190,81,3));
        colors.add(new Color(99,149,238));
        colors.add(new Color(255,191,0));
        colors.add(new Color(205,28,24));
        colors.add(new Color(50));
        colors.add(new Color(222,161,147));
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
        // barProcesses.add(new Process('a', ));
        // statProcesses.add(new Process("a", 1, 1));
        // statProcesses.add(new Process("bbb", 2, 1));
        // statProcesses.add(new Process("c", 3, 1));
        // statProcesses.add(new Process("d", 4, 1));

        // Create the main frame
        JFrame frame = new JFrame("CPU Schedulers Project");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 650);

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
        JButton testButton = new JButton("testButton");

        Dimension buttonSize = new Dimension(150, 40);
        sjfButton.setPreferredSize(buttonSize);
        pjfButton.setPreferredSize(buttonSize);
        srtfButton.setPreferredSize(buttonSize);
        fcaiButton.setPreferredSize(buttonSize);
        testButton.setPreferredSize(buttonSize);

        sjfButton.addActionListener(e -> cardLayout.show(mainPanel, "sjf"));
        pjfButton.addActionListener(e -> cardLayout.show(mainPanel, "pjf"));
        srtfButton.addActionListener(e -> cardLayout.show(mainPanel, "srtf"));
        fcaiButton.addActionListener(e -> cardLayout.show(mainPanel, "fcai"));
        testButton.addActionListener(e -> cardLayout.show(mainPanel, "colored"));

        
        menuPanel.add(Box.createRigidArea(new Dimension(800, 40)));
        menuPanel.add(mainLabel1);
        menuPanel.add(Box.createRigidArea(new Dimension(800, 0)));
        menuPanel.add(mainLabel2);
        menuPanel.add(Box.createRigidArea(new Dimension(800, 0))); 
        menuPanel.add(sjfButton);
        menuPanel.add(pjfButton);
        menuPanel.add(Box.createRigidArea(new Dimension(800, 0))); 
        menuPanel.add(srtfButton);
        menuPanel.add(fcaiButton);
        menuPanel.add(Box.createRigidArea(new Dimension(800, 0))); 
        menuPanel.add(mainLabel3);
        menuPanel.add(Box.createRigidArea(new Dimension(800, 0))); 
        menuPanel.add(mainLabelDev1);
        menuPanel.add(mainLabelDev2);
        menuPanel.add(mainLabelDev3);
        menuPanel.add(mainLabelDev4);
        menuPanel.add(mainLabelDev5);

        

        // Create the Shortest Job panel #################################################################

        JPanel sjfPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));

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
                
                // JOptionPane.showMessageDialog(
                //     null,                      
                //     "Process sent to shortest job first schedular",
                //     "Done",                       
                //     JOptionPane.INFORMATION_MESSAGE
                // );

                cardLayout.show(mainPanel, "colored");
            }

        });
        sjfadd.addActionListener(e -> {

            if(sjfname.getText().isEmpty() || sjfarival.getText().isEmpty() || sjfburst.getText().isEmpty()) {
                JOptionPane.showMessageDialog(
                    null,                         
                    "Please fill all fields",
                    "Error",                   
                    JOptionPane.ERROR_MESSAGE  
                );

            } else {

                int i = 0;
                // processes.clear();
    
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
                
                // JOptionPane.showMessageDialog(
                //     null,                      
                //     "Process sent to priority schedular",
                //     "Done",                       
                //     JOptionPane.INFORMATION_MESSAGE
                // );

                cardLayout.show(mainPanel, "colored");
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
                // processes.clear();

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
                
                // JOptionPane.showMessageDialog(
                //     null,                      
                //     "Process sent to shortest remaining time first schedular",
                //     "Done",                       
                //     JOptionPane.INFORMATION_MESSAGE
                // );

                cardLayout.show(mainPanel, "colored");
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
                // processes.clear();

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

                // System.out.println(processes.size());

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

                
                FCAIscheduler = new FCAIScheduler();
                FCAIscheduler.schedule(processes);
                
                barProcesses = FCAIscheduler.getProcessPeriods();
                statProcesses = FCAIscheduler.getExecutedProcesses();
                // System.out.println(" stat size: " + statProcesses.size());
                
                JOptionPane.showMessageDialog(
                    null,                      
                    "Process sent to FCAI schedular",
                    "Done",                       
                    JOptionPane.INFORMATION_MESSAGE
                );

                // output panel

                JLabel outputlabel1 = new JLabel("Results:");
                outputlabel1.setFont(new Font("Arial", Font.BOLD, 17));
                JLabel outputlabel2 = new JLabel("CPU Scheduling graph:");
                outputlabel2.setFont(new Font("Arial", Font.BOLD, 15));

                JPanel coloredBar = new JPanel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        Graphics2D g2d = (Graphics2D) g;

                        int panelWidth = getWidth();
                        int panelHeight = getHeight();
                        int lineHeight = 50;

                        ArrayList<Integer> segmentWidths = new ArrayList<>();

                        for (processPeriod proc : barProcesses) {
                            segmentWidths.add(proc.period);
                        }

                        ArrayList<Color> segmentColors = new ArrayList<>();
                        for (processPeriod proc : barProcesses) {
                            segmentColors.add(proc.process.color);
                        }

                        // System.out.println(segmentWidths.size());
                        // System.out.println(segmentColors.size());
                        
                        int sum = 0;
                        for (int i = 0; i < segmentWidths.size(); i++) {
                            sum += segmentWidths.get(i);
                        }
                        double scale = (400.0/sum);
                        System.out.println(sum);
                        System.out.println(scale);

                        // Draw the rectangles
                        int xOffset = 0;
                        for (int i = 0; i < segmentWidths.size(); i++) {
                            g2d.setColor(segmentColors.get(i));
                            g2d.fillRect(xOffset, (panelHeight - lineHeight) / 2, (int) (segmentWidths.get(i)*scale), lineHeight);
                            xOffset += segmentWidths.get(i)*scale;
                        }
                    }
                };
                coloredBar.setPreferredSize(new Dimension(400, 50));

                JLabel outputlabel3 = new JLabel("---------------------------------   Processes Information:   ---------------------------------");
                outputlabel3.setFont(new Font("Arial", Font.BOLD, 15));

                JPanel tablePanel = new JPanel(new GridLayout(statProcesses.size()+1, 7, 10, 10)); 
                tablePanel.add(new JLabel("Process"));
                tablePanel.add(new JLabel("color"));
                tablePanel.add(new JLabel("Name"));
                tablePanel.add(new JLabel("Priority"));
                tablePanel.add(new JLabel("Comp. time"));
                tablePanel.add(new JLabel("Waiting"));
                tablePanel.add(new JLabel("Turnaround"));

                System.out.println("stat size: " + statProcesses.size());
                
                int i = 0;
                for (Process proc : statProcesses) {
                    System.out.println("in stat loop");
                    
                    JPanel colorSquare = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
                    colorSquare.setPreferredSize(new Dimension(1, 10));
                    colorSquare.setBackground(proc.color);
                    // procLabel.setFont(new Font("Arial", Font.BOLD, 15));
                    tablePanel.add(new JLabel(Integer.toString(i)));
                    tablePanel.add(colorSquare);
                    tablePanel.add(new JLabel(proc.name));
                    tablePanel.add(new JLabel(Integer.toString(proc.priority)));
                    tablePanel.add(new JLabel(Integer.toString(proc.completionTime)));
                    tablePanel.add(new JLabel(Integer.toString(proc.waitingTime)));
                    tablePanel.add(new JLabel(Integer.toString(proc.turnaroundTime)));
                    i++;
                }

                JLabel outputlabel4 = new JLabel("-----------------------------------------    Statistics:    -----------------------------------------");
                outputlabel4.setFont(new Font("Arial", Font.BOLD, 15));
                JLabel outputlabel5 = new JLabel("Avergae waiting time:");
                outputlabel5.setFont(new Font("Arial", Font.BOLD, 12));
                JLabel outputlabel6 = new JLabel("Average turnaround time:");
                outputlabel6.setFont(new Font("Arial", Font.BOLD, 12));

                int sum = 0;
                double avgWaiting = 0;
                double avgTurnaround = 0;

                for (Process proc : statProcesses) {
                    sum += proc.waitingTime;
                }
                avgWaiting = (double) sum/statProcesses.size();

                for (Process proc : statProcesses) {
                    sum += proc.turnaroundTime;
                }
                avgTurnaround = (double) sum/statProcesses.size();

                JLabel outputlabel7 = new JLabel(Double.toString(avgWaiting));
                outputlabel7.setFont(new Font("Arial", Font.BOLD, 12));
                JLabel outputlabel8 = new JLabel(Double.toString(avgTurnaround));
                outputlabel8.setFont(new Font("Arial", Font.BOLD, 12));


                coloredPanel.add(outputlabel1);
                coloredPanel.add(Box.createRigidArea(new Dimension(800, 0)));
                coloredPanel.add(outputlabel2);
                coloredPanel.add(Box.createRigidArea(new Dimension(800, 0)));
                coloredPanel.add(coloredBar);
                coloredPanel.add(Box.createRigidArea(new Dimension(800, 0)));
                coloredPanel.add(outputlabel3);
                coloredPanel.add(Box.createRigidArea(new Dimension(800, 0)));
                coloredPanel.add(tablePanel);
                coloredPanel.add(Box.createRigidArea(new Dimension(800, 0)));
                coloredPanel.add(outputlabel4);
                coloredPanel.add(Box.createRigidArea(new Dimension(800, 0)));
                coloredPanel.add(outputlabel5);
                coloredPanel.add(outputlabel7);
                coloredPanel.add(Box.createRigidArea(new Dimension(800, 0)));
                coloredPanel.add(outputlabel6);
                coloredPanel.add(outputlabel8);

                cardLayout.show(mainPanel, "colored");
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
                // processes.clear();

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

                FCAIProcess proc = new FCAIProcess(fcainameString, fcaiarivaltime, fcaibursttime, fcaipriorityInput,fcaiquantumInput, colors.get(colorSelector++));
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

                // System.out.println(processes.size());

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
        mainPanel.add(coloredPanel, "colored");

        // Add main panel to frame
        frame.add(mainPanel);

        // Show the frame
        frame.setVisible(true);

           
    }
}
