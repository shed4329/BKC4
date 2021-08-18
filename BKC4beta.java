import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BKC4beta {
    public static void main(String[] args) {
        showUI window = new showUI();
        window.start();
    }
}

class showUI {
    private final String[] keys = {"%", "CE", "C", "Back", "1⁄x", "X²", "√x", "÷", "7", "8", "9", "X", "4", "5", "6", "-", "1", "2", "3", "+", "+/-", "0", ".", "="};
    private final JButton[] buttons = new JButton[keys.length];
    private double numberA;
    private double numberB;
    private int kind;
    private String showText = "";
    private String resultText = "";
    private int times;
    private int pointTimes;
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JTextField textField = new JTextField();

    public void start() {
    	   //窗口设置
        JFrame frame = new JFrame("计算器");        
        frame.setSize(300, 500);
        frame.setVisible(true);
        frame.setIconImage(new ImageIcon("32.png").getImage());
        //设置菜单栏
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(238, 237, 237));
        frame.setJMenuBar(menuBar);
        //外观菜单栏
        JMenu LookMenu = new JMenu("外观");
        menuBar.add(LookMenu);
        JMenuItem metal = new JMenuItem("金属");
        LookMenu.add(metal);
        JMenuItem nimbus = new JMenuItem("圆润");
        LookMenu.add(nimbus);
        JMenuItem system = new JMenuItem("系统");
        LookMenu.add(system);
        JMenuItem normal = new JMenuItem("默认");
        LookMenu.add(normal);
        metal.addActionListener(e -> changeLook("javax.swing.plaf.metal.MetalLookAndFeel", frame));
        nimbus.addActionListener(e -> changeLook("javax.swing.plaf.nimbus.NimbusLookAndFeel", frame));
        system.addActionListener(e -> changeLook(UIManager.getSystemLookAndFeelClassName(), frame));
        normal.addActionListener(e -> {
            changeLook("javax.swing.plaf.metal.MetalLookAndFeel", frame);
            setPanel(frame);
        });
        //设置菜单栏
        JMenu SettingMenu = new JMenu("设置");        
        menuBar.add(SettingMenu);
        JMenuItem about = new JMenuItem("关于");
        SettingMenu.add(about);
        about.addActionListener(e -> JOptionPane.showMessageDialog(null, "BKC4 beta 4.01", "关于", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("32.png")));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        setPanel(frame);
    }
    public void setPanel(JFrame frame){
        Dimension dimension = frame.getSize();//获取当前窗口大小
        panel1.setLayout(new BorderLayout());//panel1放文本框
        panel1.setPreferredSize(new Dimension((int) dimension.getWidth(), (int) (dimension.getHeight() / 4)));//设置面板大小
        frame.add(panel1);
        frame.add(panel2);
        frame.getContentPane().add("North", panel1);
        frame.getContentPane().add("Center", panel2);
        textField.setFont(new Font("宋体", Font.BOLD, 23));//设置文本框
        textField.setBorder(null);//边框去掉
        textField.setEditable(false);//不能编辑
        textField.setBackground(new Color(235, 233, 232));
        panel1.add(textField);
        changeMode(panel2);
    }
    public void setTextField(String otherText, int number) {
        if (number == 0) {//表示增加字符
            showText = showText + otherText;
            textField.setText(showText);
        } else if (number == 1) {//表示更改字符,用于计算输出结果
            resultText = otherText;
            showText = otherText;
            textField.setText(resultText);
        } else if (number == 2) {//表示减少字符
            showText = otherText;
            textField.setText(showText);
        } else {//表示替换字符
            showText = otherText;
            textField.setText(showText);
        }
    }

    public void buttonStandard(JPanel panel) {
        for (int i = 0; i <= 23; i++) {
            buttons[i] = new JButton(keys[i]);
            buttons[i].setBorder(null);
            //设置一点按钮的细节
            if (i >7 && (i + 1) % 4 != 0){
                buttons[i].setBackground(new Color(243, 243, 243));
            }else if(i==23){
                buttons[i].setBackground(new Color(131, 189, 196));
            }else{
                buttons[i].setBackground(new Color(218, 218, 218));
            }
            buttons[i].addActionListener(new buttonStandardActionListener());
            panel.add(buttons[i]);
        }
    }

    public void changeLook(String text, JFrame frame) {
        try {
            UIManager.setLookAndFeel(text);
            SwingUtilities.updateComponentTreeUI(frame);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"当前环境不支持该外观","错误",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void changeMode(JPanel panel) {
        panel.removeAll();
        panel.repaint();
        panel.setLayout(new GridLayout(6, 4, 1, 1));
        buttonStandard(panel);
        panel.updateUI();
    }

    public void setNumberA(int number) {
        if (pointTimes == 0) {
            numberA = numberA * 10 + number;
            setTextField(Integer.toString(number), 0);
        } else {//小数
            String Text = showText;
            Text = Text + number;
            Text = expectPoint(Text);
            setPoint(Text, number);
        }

    }

    public String expectPoint(String Text){
        if (Text.contains("+") | Text.contains("-")|Text.contains("X") |Text.contains("/")) {
            if (Text.contains("+")) {
                Text = Text.substring(Text.indexOf("+") + 1);
            }else if(Text.contains("-")){
                Text = Text.substring(Text.indexOf("-") + 1);
            }else if (Text.contains("X")) {
                Text = Text.substring(Text.indexOf("X") + 1);
            }else {
                Text = Text.substring(Text.indexOf("/") + 1);
            }
        }
        return Text;
    }

    public void stringCheck(String action) {
        if(numberA!=0&&numberB!=0){//不按等号连续运算
            calculate();
            numberA = 0;
        }
        kind = switchString(action);
        setTextField(action, 0);
        pointTimes = 0;
    }

    public int switchString(String action){
        if(numberA!=0) {
            numberB = numberA;
            numberA = 0;
        }
        int operatorKind;
        switch (action) {
            case "+":
                operatorKind = 1;
                break;
            case "-":
                operatorKind = 2;
                break;
            case "X":
                operatorKind = 3;
                break;
            default:
                operatorKind = 4;
                break;
        }
        return operatorKind;
    }
    public void changeCharacter() {//改变正负号
        if (numberB == 0) {//未按下+-x/
            numberA = 0 - numberA;
            setTextField("" + numberA, 2);
        } else if (numberA != 0 && numberB != 0) {//按下加减乘除
            int indexOther;
            if (numberA % 1 == 0) {//numberA为整数
                indexOther = showText.indexOf(Integer.toString((int) numberA));//查找位置,如果直接使用double查找，返回值必为-1
            } else {//小数
                indexOther = showText.indexOf("" + numberA);
            }
            StringBuilder stringBuilder = new StringBuilder(showText);//String转换成StringBuilder编辑，再转换成String
            stringBuilder.insert(indexOther, "-");
            showText = stringBuilder.toString();
            setTextField(showText, 3);
            numberA = 0 - numberA;
        } else {//按下符号后再按下+/-
            numberA = 0 - numberB;
            setTextField(""+numberA , 0);
        }
    }

    public void calculate() {
        if (kind == 1) {//加法
            numberB = numberB + numberA;
        } else if (kind == 2) {
            numberB = numberB - numberA;
        } else if (kind == 3) {
            numberB = numberB * numberA;
        } else if (kind == 4) {
            numberB = numberB / numberA;
        } else {
            numberB = numberA;
            numberA = 0;
        }
        times = 1;
        kind=0;
        setTextField("" + numberB, 1);
    }

    public void setPoint(String Text, int number) {
        Text = Text.substring(Text.indexOf(".") + 1);
        int adder = numberChange(Text.length());
        double addTimes = 1.0 / adder;
        numberA = numberA + (number * addTimes);
        setTextField("" + number, 0);
    }

    public void calculate(String action) {
        switch (action) {
            case "%": {
                if (numberA == 0 && numberB != 0) {//对应按下运算符后和计算完成后
                    if (times == 0) {
                        numberA = numberB / 100;
                        setTextField("" + numberA, 0);
                    } else {//计算完成后
                        numberB = numberB / 100;
                        setTextField("" + numberB, 1);
                    }
                } else if (numberA != 0 && numberB != 0) {//对应按下第二个数
                    int index = showText.indexOf(Integer.toString((int) numberA));
                    String text = showText.substring(0, index);
                    numberA = numberA / 100;
                    text = text + numberA;
                    setTextField(text, 3);
                } else if (numberA != 0 && numberB == 0) {//按下了平方之类的键
                    numberA = 0;
                    numberB = 0;
                    setTextField("", 1);
                }
                break;
            }
            case "X²": {
                numberA = numberA * numberA;
                setTextField("" + numberA, 1);
                break;
            }
            case "1⁄x": {
                numberA = 1 / numberA;
                setTextField("" + numberA, 1);
                break;
            }
            case "√x": {
                numberA = Math.sqrt(numberA);
                setTextField("" + numberA, 1);
                break;
            }
        }
    }

    public void back() {
        //还没有添加小数部分
        if (numberA != 0&&numberA%1==0) {
            if (numberA >= 0) {
                numberA = Math.floor(numberA / 10);
            }else {
                numberA = Math.ceil(numberA / 10);
            }

            if(numberB==0){
                setTextField("" + numberA, 3);
            }else{
                showText = showText.substring(0, showText.length() - 1);
                setTextField(showText, 2);
            }
        }else if(numberA!=0&&numberA%1!=0){
            String Text = showText.substring(showText.lastIndexOf(".")+1);
            double lessTime = 1.0/numberChange(Text.length());
            int number = Integer.parseInt(Text.substring(Text.length()-1));
            numberA = numberA-(number*lessTime);
            setTextField(""+numberA,3);
        }
    }

    public void clean(String action) {
        if (action.equals("C")) {//c键
            c();
        } else {//ce键
            if (showText.contains("+") |showText.contains("-")|showText.contains("X") || showText.contains("/")) {
                ce();
            } else {//完成计算后按下ce
                c();
            }
        }
        pointTimes = 0;
    }
    public void c(){
        numberA = 0;
        numberB = 0;
        setTextField("", 1);
        showText = "";
        resultText = "";
        pointTimes = 0;
    }
    public void ce() {
        if (kind == 1) {
            int indexA = showText.indexOf("+");
            setTextField(showText.substring(0, indexA + 1), 2);
        } else if (kind == 2) {
            int indexB = showText.indexOf("-");
            setTextField(showText.substring(0, indexB + 1), 2);
        } else if (kind == 3) {
            int indexC = showText.indexOf("X");
            setTextField(showText.substring(0, indexC + 1), 2);
        } else {
            int indexD = showText.indexOf("/");
            setTextField(showText.substring(0, indexD + 1), 2);
        }
        numberA = 0;
    }

    public void point() {
        if (pointTimes == 0) {
            pointTimes = 1;
            setTextField(".", 0);
        }
    }

    public int numberChange(int number) {
        int i;
        int j = 1;
        for (i = 1; i <= number; i++) {
            j = j * 10;
        }
        return j;
    }

    class buttonStandardActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String action = e.getActionCommand();
            if ("0123456789".contains(action)) {//按下数字键
                int number = Integer.parseInt(action);
                setNumberA(number);
            } else if (action.equals(keys[0]) | action.equals(keys[4]) | action.equals(keys[5]) | action.equals(keys[6])) {//按下了只需要一个数的运算
                calculate(action);
            } else if (action.equals(keys[1]) | action.equals(keys[2])) {//按下c，ce
                clean(action);
            } else if (action.equals(keys[3])) {//按下了back键
                back();
            } else if (action.equals(keys[7]) | action.equals(keys[11]) | action.equals(keys[15]) | action.equals(keys[19])) {//按下了加减乘除
                stringCheck(action);
            } else if (action.equals(keys[20])) {//按下变号键
                changeCharacter();
            } else if (action.equals(keys[22])) {//按下了小数点
                point();
            } else {//按下了等于键
                if (numberB != 0 && numberA != 0) {
                    calculate();
                    numberA = 0;
                }
            }
        }
    }
}