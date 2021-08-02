package MyPackage.help;

import MyPackage.My_First_Plugin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

/*
 * Created by JFormDesigner on Mon Jul 26 17:26:09 CST 2021
 */



/**
 * @author unknown
 */
public class myGUI extends JFrame {
    public myGUI() {
        initComponents();
        System.out.println("in");
    }

    private void button1ActionPerformed(ActionEvent e) {
        // TODO add your code here
        System.out.println("click button parm set");
        String knownPixels = knownPixelsContext.getText();
        String ingroupDistance = inGroupDistanceContext.getText();
        MyParm.knownPixels = knownPixels;
        MyParm.inGroupDistance = ingroupDistance;
    }

    private void button5ActionPerformed(ActionEvent e) {
        // TODO add your code here
        JFileChooser fc=new JFileChooser("F:\\");
        int val=fc.showOpenDialog(null);    //文件打开对话框
        if(val==fc.APPROVE_OPTION)
        {
            //正常选择文件
            String filePath = fc.getSelectedFile().toString();
            labelPath.setText(filePath);
            //System.out.println(filePath);
            MyParm.path = filePath;

        }
    }

    private void button2ActionPerformed(ActionEvent e) {
        String knownPixels = knownPixelsContext.getText();
        String ingroupDistance = inGroupDistanceContext.getText();
        //System.out.println(knownDictance+"_"+knownPixels+"_"+ingroupDistance);

        MyParm.knownPixels = knownPixels;
        MyParm.inGroupDistance = ingroupDistance;
        Integer linkageLength = slider3.getValue();
        Integer cropLength = slider4.getValue();
        MyParm.linkageLength = linkageLength;
        MyParm.cropLength = cropLength;
        My_First_Plugin myObj = new My_First_Plugin();
        myObj.runCropAndLink(MyParm.path);

    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        dialogPane = new JPanel();
        panel3 = new JPanel();
        knownPixelNumberLabel = new JLabel();
        knownPixelsContext = new JTextField();
        inGroupDistanceLabel = new JLabel();
        inGroupDistanceContext = new JTextField();
        panel4 = new JPanel();
        label9 = new JLabel();
        button5 = new JButton();
        label6 = new JLabel();
        labelPath = new JLabel();
        panel5 = new JPanel();
        label8 = new JLabel();
        cropSlider = new JSlider();
        label7 = new JLabel();
        linkageSlider = new JSlider();
        label10 = new JLabel();
        setParmButton = new JButton();
        button2 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing. border .EmptyBorder ( 0
            , 0 ,0 , 0) ,  "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn" , javax. swing .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM
            , new java. awt .Font ( "Dia\u006cog", java .awt . Font. BOLD ,12 ) ,java . awt. Color .red ) ,
            dialogPane. getBorder () ) ); dialogPane. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e
            ) { if( "\u0062ord\u0065r" .equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } );
            dialogPane.setLayout(new GridBagLayout());
            ((GridBagLayout)dialogPane.getLayout()).columnWidths = new int[] {296, 0, 0, 0, 88, 0};
            ((GridBagLayout)dialogPane.getLayout()).rowHeights = new int[] {52, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            ((GridBagLayout)dialogPane.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
            ((GridBagLayout)dialogPane.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

            //======== panel3 ========
            {
                panel3.setToolTipText("Set up");
                panel3.setFont(new Font("Times New Roman", Font.PLAIN, 16));
                panel3.setName("Set up");
                panel3.setLayout(new GridBagLayout());
                ((GridBagLayout)panel3.getLayout()).columnWidths = new int[] {296, 0, 0, 0};
                ((GridBagLayout)panel3.getLayout()).rowHeights = new int[] {52, 41, 0};
                ((GridBagLayout)panel3.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0, 1.0E-4};
                ((GridBagLayout)panel3.getLayout()).rowWeights = new double[] {0.0, 1.0, 1.0E-4};

                //---- knownPixelNumberLabel ----
                knownPixelNumberLabel.setText("        Pixel size(nm)");
                knownPixelNumberLabel.setAlignmentX(0.5F);
                panel3.add(knownPixelNumberLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));

                //---- knownPixelsContext ----
                knownPixelsContext.setText("8");
                panel3.add(knownPixelsContext, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));

                //---- inGroupDistanceLabel ----
                inGroupDistanceLabel.setText("       Puncta cluster distance(nm)");
                panel3.add(inGroupDistanceLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));

                //---- inGroupDistanceContext ----
                inGroupDistanceContext.setText("40");
                panel3.add(inGroupDistanceContext, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(panel3, new GridBagConstraints(0, 0, 3, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));

            //======== panel4 ========
            {
                panel4.setLayout(new GridBagLayout());
                ((GridBagLayout)panel4.getLayout()).columnWidths = new int[] {296, 0, 0, 0, 273, 0};
                ((GridBagLayout)panel4.getLayout()).rowHeights = new int[] {0, 0, 0};
                ((GridBagLayout)panel4.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
                ((GridBagLayout)panel4.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};

                //---- label9 ----
                label9.setText("     Import localization matrix");
                panel4.add(label9, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));

                //---- button5 ----
                button5.setText("path");
                button5.addActionListener(e -> button5ActionPerformed(e));
                panel4.add(button5, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));

                //---- label6 ----
                label6.setText("    Check your import path");
                panel4.add(label6, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
                panel4.add(labelPath, new GridBagConstraints(2, 1, 3, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(panel4, new GridBagConstraints(0, 2, 5, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));

            //======== panel5 ========
            {
                panel5.setLayout(new GridBagLayout());
                ((GridBagLayout)panel5.getLayout()).columnWidths = new int[] {296, 0, 0, 0};
                ((GridBagLayout)panel5.getLayout()).rowHeights = new int[] {0, 0, 0, 0};
                ((GridBagLayout)panel5.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0, 1.0E-4};
                ((GridBagLayout)panel5.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};

                //---- label8 ----
                label8.setText("    Crop duration");
                panel5.add(label8, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));

                //---- cropSlider ----
                cropSlider.setPaintLabels(true);
                cropSlider.setPaintTicks(true);
                cropSlider.setSnapToTicks(true);
                panel5.add(cropSlider, new GridBagConstraints(1, 0, 2, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));

                //---- label7 ----
                label7.setText("    Linkage duration");
                panel5.add(label7, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));

                //---- linkageSlider ----
                linkageSlider.setAlignmentX(5.0F);
                linkageSlider.setAlignmentY(5.0F);
                linkageSlider.setPaintLabels(true);
                linkageSlider.setPaintTicks(true);
                panel5.add(linkageSlider, new GridBagConstraints(1, 2, 2, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(panel5, new GridBagConstraints(0, 4, 3, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));

            //---- label10 ----
            label10.setText("Processing progress");
            dialogPane.add(label10, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));

            //---- setParmButton ----
            setParmButton.setText("set parm");
            setParmButton.addActionListener(e -> button1ActionPerformed(e));
            dialogPane.add(setParmButton, new GridBagConstraints(2, 7, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));

            //---- button2 ----
            button2.setText("refresh");
            button2.addActionListener(e -> button2ActionPerformed(e));
            dialogPane.add(button2, new GridBagConstraints(4, 7, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        }
        contentPane.add(dialogPane, BorderLayout.WEST);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel dialogPane;
    private JPanel panel3;
    private JLabel knownPixelNumberLabel;
    private JTextField knownPixelsContext;
    private JLabel inGroupDistanceLabel;
    private JTextField inGroupDistanceContext;
    private JPanel panel4;
    private JLabel label9;
    private JButton button5;
    private JLabel label6;
    private JLabel labelPath;
    private JPanel panel5;
    private JLabel label8;
    private JSlider cropSlider;
    private JLabel label7;
    private JSlider linkageSlider;
    private JLabel label10;
    private JButton setParmButton;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables




    public static void main(String[] args) {
        JFrame jf = new myGUI();
        jf.setVisible(true);
    }
}