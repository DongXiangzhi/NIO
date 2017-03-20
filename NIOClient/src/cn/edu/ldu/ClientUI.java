package cn.edu.ldu;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * 功能：石头剪刀布客户机
 * 设计：董相志 版权所有2016--2018,upsunny2008@163.com
 */
public class ClientUI extends javax.swing.JFrame {
    private ByteBuffer recvBuff=ByteBuffer.allocate(1024); //接收缓冲区
    private ByteBuffer sendBuff=ByteBuffer.allocate(1024); //发送缓冲区
    private SocketChannel clientChannel=null; //会话通道
    private Charset charset=Charset.forName("UTF-8");
    private static int playerWin=0; //玩家胜
    private static int serverWin=0; //服务器胜
    private static int playerDraw=0; //玩家平
    private static int serverDraw=0;  //服务器平  

    /**
     * Creates new form GameClient
     */
    public ClientUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtRemoteName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtRemotePort = new javax.swing.JTextField();
        btnConnect = new javax.swing.JButton();
        midPanel = new javax.swing.JPanel();
        actionPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnScissors = new javax.swing.JButton();
        btnStone = new javax.swing.JButton();
        btnPaper = new javax.swing.JButton();
        playerPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblPlayerChoice = new javax.swing.JLabel();
        lblPlayerScore = new javax.swing.JLabel();
        serverPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblServerChoice = new javax.swing.JLabel();
        lblServerScore = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("石头剪刀布玩家（客户机）--设计：董相志，版权所有，2016-2018");
        setPreferredSize(new java.awt.Dimension(900, 600));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        topPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "通信面板", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("宋体", 1, 18))); // NOI18N
        topPanel.setFont(new java.awt.Font("宋体", 1, 18)); // NOI18N
        topPanel.setPreferredSize(new java.awt.Dimension(600, 100));

        jLabel4.setFont(new java.awt.Font("宋体", 1, 16)); // NOI18N
        jLabel4.setText("服务器主机名：");

        txtRemoteName.setFont(new java.awt.Font("宋体", 1, 16)); // NOI18N
        txtRemoteName.setText("localhost");

        jLabel5.setFont(new java.awt.Font("宋体", 1, 16)); // NOI18N
        jLabel5.setText("服务器端口：");

        txtRemotePort.setFont(new java.awt.Font("宋体", 1, 16)); // NOI18N
        txtRemotePort.setText("20000");
        txtRemotePort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRemotePortActionPerformed(evt);
            }
        });

        btnConnect.setFont(new java.awt.Font("宋体", 1, 16)); // NOI18N
        btnConnect.setText("连接服务器");
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRemoteName, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                .addGap(37, 37, 37)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRemotePort, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                .addGap(52, 52, 52)
                .addComponent(btnConnect)
                .addContainerGap())
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtRemoteName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtRemotePort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConnect))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(topPanel, java.awt.BorderLayout.PAGE_START);

        midPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "游戏面板", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("宋体", 1, 18))); // NOI18N

        actionPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        actionPanel.setPreferredSize(new java.awt.Dimension(200, 100));

        jLabel1.setFont(new java.awt.Font("宋体", 1, 18)); // NOI18N
        jLabel1.setText(" 请 出 拳 ");

        btnScissors.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cn/edu/ldu/scissors.JPG"))); // NOI18N
        btnScissors.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnScissorsActionPerformed(evt);
            }
        });

        btnStone.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cn/edu/ldu/stone.JPG"))); // NOI18N
        btnStone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStoneActionPerformed(evt);
            }
        });

        btnPaper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cn/edu/ldu/paper.JPG"))); // NOI18N
        btnPaper.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPaperActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout actionPanelLayout = new javax.swing.GroupLayout(actionPanel);
        actionPanel.setLayout(actionPanelLayout);
        actionPanelLayout.setHorizontalGroup(
            actionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(actionPanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(actionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(actionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jLabel1)
                        .addComponent(btnStone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnPaper, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnScissors, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        actionPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnPaper, btnScissors, btnStone});

        actionPanelLayout.setVerticalGroup(
            actionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(actionPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnStone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnScissors, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPaper, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        playerPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        playerPanel.setPreferredSize(new java.awt.Dimension(200, 100));

        jLabel2.setFont(new java.awt.Font("宋体", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("玩 家");

        lblPlayerChoice.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cn/edu/ldu/PaperLeft.png"))); // NOI18N
        lblPlayerChoice.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblPlayerChoice.setPreferredSize(new java.awt.Dimension(150, 200));

        lblPlayerScore.setFont(new java.awt.Font("宋体", 1, 18)); // NOI18N
        lblPlayerScore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlayerScore.setText("0 胜 0 平");

        javax.swing.GroupLayout playerPanelLayout = new javax.swing.GroupLayout(playerPanel);
        playerPanel.setLayout(playerPanelLayout);
        playerPanelLayout.setHorizontalGroup(
            playerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(playerPanelLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(playerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPlayerChoice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPlayerScore, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13))
        );
        playerPanelLayout.setVerticalGroup(
            playerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(playerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(lblPlayerChoice, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(lblPlayerScore)
                .addGap(82, 82, 82))
        );

        serverPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        serverPanel.setPreferredSize(new java.awt.Dimension(200, 100));

        jLabel3.setFont(new java.awt.Font("宋体", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("服务器");

        lblServerChoice.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblServerChoice.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cn/edu/ldu/StoneRight.png"))); // NOI18N
        lblServerChoice.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblServerChoice.setPreferredSize(new java.awt.Dimension(150, 200));

        lblServerScore.setFont(new java.awt.Font("宋体", 1, 18)); // NOI18N
        lblServerScore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblServerScore.setText("0 胜  0 平");

        javax.swing.GroupLayout serverPanelLayout = new javax.swing.GroupLayout(serverPanel);
        serverPanel.setLayout(serverPanelLayout);
        serverPanelLayout.setHorizontalGroup(
            serverPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, serverPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(serverPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblServerScore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblServerChoice, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE))
                .addContainerGap())
        );
        serverPanelLayout.setVerticalGroup(
            serverPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(serverPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(19, 19, 19)
                .addComponent(lblServerChoice, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(lblServerScore)
                .addGap(81, 81, 81))
        );

        javax.swing.GroupLayout midPanelLayout = new javax.swing.GroupLayout(midPanel);
        midPanel.setLayout(midPanelLayout);
        midPanelLayout.setHorizontalGroup(
            midPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(midPanelLayout.createSequentialGroup()
                .addComponent(actionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(playerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(serverPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                .addContainerGap())
        );
        midPanelLayout.setVerticalGroup(
            midPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(serverPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
            .addComponent(playerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
            .addComponent(actionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
        );

        getContentPane().add(midPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //出布
    private void btnPaperActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaperActionPerformed
        if (clientChannel==null) {
            JOptionPane.showMessageDialog(null, "请先连接服务器","错误提示",JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            //显示玩家图片PaperLeft
            URL imageUrl=ClientUI.class.getResource("PaperLeft.png");
            ImageIcon paperPic=new ImageIcon(imageUrl);
            lblPlayerChoice.setIcon(paperPic); 
            sendBuff.clear(); //清空发送缓冲区
            sendBuff=ByteBuffer.wrap("Paper".getBytes(charset)); //Paper字符串包装到缓冲区
            clientChannel.write(sendBuff); //首先告诉服务器自己的选择
            recvBuff.clear(); //清空接收缓冲区
            clientChannel.read(recvBuff);//接收来自服务器的回复
            recvBuff.flip(); //指针回到数据起点
            String serverSide=charset.decode(recvBuff).toString(); //解码服务器返回字符串
            //根据服务器选择显示图片
            String imageName=serverSide.substring(0, serverSide.indexOf("#"))+"Right.png";
            URL imageServerUrl=ClientUI.class.getResource(imageName);
            ImageIcon serverPic=new ImageIcon(imageServerUrl);        
            lblServerChoice.setIcon(serverPic);
            //根据输赢结果更新胜局数和平局数
            String result=serverSide.substring(serverSide.indexOf("#")+1);
            if (result.equalsIgnoreCase("TwoDraw")) {
                playerDraw++;
                serverDraw++;
            } else if (result.equalsIgnoreCase("ClientWin")) {
                playerWin++;
            } else if (result.equalsIgnoreCase("ServerWin")) {
                serverWin++;
            }
            String playerScore=playerWin+" 胜 "+playerDraw+" 平";
            String serverScore=serverWin+" 胜 "+serverDraw+" 平";
            lblPlayerScore.setText(playerScore);
            lblServerScore.setText(serverScore);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"错误提示",JOptionPane.ERROR_MESSAGE);
        }                     
    }//GEN-LAST:event_btnPaperActionPerformed
    //出石头
    private void btnStoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStoneActionPerformed
        if (clientChannel==null) {
            JOptionPane.showMessageDialog(null, "请先连接服务器","错误提示",JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            //显示玩家图片StoneLeft.png
            URL imageUrl=ClientUI.class.getResource("StoneLeft.png");
            ImageIcon stonePic=new ImageIcon(imageUrl);
            lblPlayerChoice.setIcon(stonePic);
            sendBuff.clear();//清空发送缓冲区
            sendBuff=ByteBuffer.wrap("Stone".getBytes(charset));//Stone字符串包装到缓冲区
            clientChannel.write(sendBuff); //首先告诉服务器自己的选择
            recvBuff.clear();//清空接收缓冲区
            clientChannel.read(recvBuff);//接收来自服务器的回复
            recvBuff.flip(); //指针回至收到数据的起点
            String serverSide=charset.decode(recvBuff).toString();//解码收到的字符串
            //求解文件名，显示服务器图片
            String imageName=serverSide.substring(0, serverSide.indexOf("#"))+"Right.png";
            URL imageServerUrl=ClientUI.class.getResource(imageName);
            ImageIcon serverPic=new ImageIcon(imageServerUrl);        
            lblServerChoice.setIcon(serverPic);
            //求解服务器返回的比赛结果，根据结果更新胜局和平局数
            String result=serverSide.substring(serverSide.indexOf("#")+1);
            if (result.equalsIgnoreCase("TwoDraw")) {
                playerDraw++;
                serverDraw++;
            } else if (result.equalsIgnoreCase("ClientWin")) {
                playerWin++;
            } else if (result.equalsIgnoreCase("ServerWin")) {
                serverWin++;
            }
            String playerScore=playerWin+" 胜 "+playerDraw+" 平";
            String serverScore=serverWin+" 胜 "+serverDraw+" 平";
            lblPlayerScore.setText(playerScore);
            lblServerScore.setText(serverScore);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"错误提示",JOptionPane.ERROR_MESSAGE);
        }       
    }//GEN-LAST:event_btnStoneActionPerformed
    //出剪刀
    private void btnScissorsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnScissorsActionPerformed
        if (clientChannel==null) {
            JOptionPane.showMessageDialog(null, "请先连接服务器","错误提示",JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            //显示玩家图片ScissorsLeft
            URL imageUrl=ClientUI.class.getResource("ScissorsLeft.png");
            ImageIcon scissorsPic=new ImageIcon(imageUrl);
            lblPlayerChoice.setIcon(scissorsPic);
            sendBuff.clear(); //清空发送缓冲区
            sendBuff=ByteBuffer.wrap("Scissors".getBytes(charset)); //字符串包装到缓冲区
            clientChannel.write(sendBuff); //首先告诉服务器自己的选择
            recvBuff.clear(); //清空接收缓冲区
            clientChannel.read(recvBuff);//接收来自服务器的回复
            recvBuff.flip(); //指针回至收到数据的起点
            //解码收到的字符串
            String serverSide=charset.decode(recvBuff).toString();
            //求解文件名，显示服务器图片
            String imageName=serverSide.substring(0, serverSide.indexOf("#"))+"Right.png";
            URL imageServerUrl=ClientUI.class.getResource(imageName);
            ImageIcon serverPic=new ImageIcon(imageServerUrl);        
            lblServerChoice.setIcon(serverPic);
            //求解服务器返回的比赛结果，根据结果更新胜局和平局数
            String result=serverSide.substring(serverSide.indexOf("#")+1);
            if (result.equalsIgnoreCase("TwoDraw")) {
                playerDraw++;
                serverDraw++;
            } else if (result.equalsIgnoreCase("ClientWin")) {
                playerWin++;
            } else if (result.equalsIgnoreCase("ServerWin")) {
                serverWin++;
            }
            String playerScore=playerWin+" 胜 "+playerDraw+" 平";
            String serverScore=serverWin+" 胜 "+serverDraw+" 平";
            lblPlayerScore.setText(playerScore);
            lblServerScore.setText(serverScore);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"错误提示",JOptionPane.ERROR_MESSAGE);
        }             
    
    }//GEN-LAST:event_btnScissorsActionPerformed
    //连接服务器
    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectActionPerformed
    try {
        //构建远程服务器地址
        String remoteName=txtRemoteName.getText();
        int remotePort=Integer.parseInt(txtRemotePort.getText());
        SocketAddress remoteAddr=new InetSocketAddress(InetAddress.getByName(remoteName),remotePort);
        clientChannel=SocketChannel.open(); //创建客户机会话通道
        clientChannel.connect(remoteAddr); //连接远程服务器  
    } catch (IOException ex) {
       JOptionPane.showMessageDialog(null, ex.getMessage(), "连接错误", JOptionPane.ERROR_MESSAGE);
       return;
    } 
    btnConnect.setEnabled(false);
    }//GEN-LAST:event_btnConnectActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

    }//GEN-LAST:event_formWindowClosing

    private void txtRemotePortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRemotePortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRemotePortActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ClientUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel actionPanel;
    private javax.swing.JButton btnConnect;
    private javax.swing.JButton btnPaper;
    private javax.swing.JButton btnScissors;
    private javax.swing.JButton btnStone;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblPlayerChoice;
    private javax.swing.JLabel lblPlayerScore;
    private javax.swing.JLabel lblServerChoice;
    private javax.swing.JLabel lblServerScore;
    private javax.swing.JPanel midPanel;
    private javax.swing.JPanel playerPanel;
    private javax.swing.JPanel serverPanel;
    private javax.swing.JPanel topPanel;
    private javax.swing.JTextField txtRemoteName;
    private javax.swing.JTextField txtRemotePort;
    // End of variables declaration//GEN-END:variables
}
