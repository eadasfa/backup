package qi.clickscren;


   
 import java.awt.Insets;  
 import java.awt.event.ActionEvent;  
 import java.awt.event.ActionListener;  
   
 import javax.swing.JButton;  
 import javax.swing.JFrame;  
 import javax.swing.JOptionPane;  
   
 import com.melloware.jintellitype.HotkeyListener;  
 import com.melloware.jintellitype.JIntellitype;  
   
 /**  
  * ����JIntellitypeʵ��ȫ���ȼ�����  
  * @author Jeby Sun  
  *  
  */  
 public class GlobleHotKeyDemo extends JFrame {  
   
     private static final long serialVersionUID = 1L;  
       
     //�����ȼ���ʶ�����������ö���ȼ�ʱ�����¼������������û����µ��ȼ�  
     public static final int FUNC_KEY_MARK = 1;  
     public static final int EXIT_KEY_MARK = 0;  
       
     JButton msgBtn;  
     JButton exitBtn;  
   
     public GlobleHotKeyDemo() {  
         this.setTitle("ȫ���ȼ�����");  
         this.setBounds(100, 100, 600, 400);  
         this.setLayout(null);  
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
         
         msgBtn = new JButton("������Alt+S��");  
        //���ð�ť�߾�  
         msgBtn.setMargin(new Insets(0,0,0,0));  
         msgBtn.setFocusable(false);  
         msgBtn.setBounds(20, 20, 120, 30);  
         msgBtn.addActionListener(new ActionListener() {  
          
             public void actionPerformed(ActionEvent e) {  
                showMessage();  
            }  
         });  
        this.add(msgBtn);  
           
         exitBtn = new JButton("�˳���Alt+Q��");  
        exitBtn.setMargin(new Insets(0,0,0,0));  
         exitBtn.setFocusable(false);  
         exitBtn.setBounds(160, 20, 120, 30);  
        exitBtn.addActionListener(new ActionListener() {  
            
            public void actionPerformed(ActionEvent e) {  
                System.exit(0);  
            }  
        });  
        this.add(exitBtn);  
          
        //��һ����ע���ȼ�����һ��������ʾ���ȼ��ı�ʶ���ڶ���������ʾ��ϼ������û����Ϊ0������������Ϊ�������Ҫ�ȼ�  
        JIntellitype.getInstance().registerHotKey(FUNC_KEY_MARK, JIntellitype.MOD_ALT, (int)'S');    
        JIntellitype.getInstance().registerHotKey(EXIT_KEY_MARK, JIntellitype.MOD_ALT, (int)'Q');    
           
        //�ڶ���������ȼ�������  
        JIntellitype.getInstance().addHotKeyListener(new HotkeyListener() {  
              
          
            public void onHotKey(int markCode) {  
                 switch (markCode) {    
                 case FUNC_KEY_MARK:    
                     showMessage();  
                     break;    
                 case EXIT_KEY_MARK:    
                     System.exit(0);  
                     break;     
                 }                   
             }  
         });    
           
         this.setVisible(true);  
     }  
       
     public void showMessage() {  
         JOptionPane.showMessageDialog(null, "����Ѵ�����С��������ݼ�Alt+SҲ���Ե�����ʾ��Ŷ��", "���������", JOptionPane.INFORMATION_MESSAGE);  
     }  
       
       
   
     public static void main(String[] args) {  
         new GlobleHotKeyDemo();  
     }  
 }  