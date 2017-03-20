package cn.edu.ldu;
import java.util.Random;
/**
 * 功能：石头剪刀布协议
 * 设计：董相志 版权所有2016--2018,upsunny2008@163.com
 */
public class Protocol {    
    public String protocolWorking(String clientSide) {
        String serverSide=null; //服务器的选择
        String result=null; //单局比赛结果
        String answer=null; //返回结果，结果形式：服务器选择#输赢和平局，例如：Stone#ServerWin
        //用随机数0\1\2模拟服务器的选择,0表示石头，1表示剪刀，2表示布
        Random random=new Random(); 
        int serverChoice=random.nextInt(3);//生成0--2之间的随机整数
        switch (serverChoice) {
            case 0: //服务器出石头，判断胜负
                serverSide="Stone";
                if (clientSide.equalsIgnoreCase("Stone")) {
                    result="TwoDraw"; //平局
                } else if (clientSide.equalsIgnoreCase("Scissors")) {
                    result="ServerWin"; //服务器赢
                } else {
                    result="ClientWin"; //玩家赢
                }
                break;
            case 1: //服务器出剪刀，判断胜负
                serverSide="Scissors";
                if (clientSide.equalsIgnoreCase("Stone")) {
                    result="ClientWin";
                } else if (clientSide.equalsIgnoreCase("Scissors")) {
                    result="TwoDraw";
                } else {
                    result="ServerWin";
                }               
                break;
            case 2: //服务器出布，判断胜负
                serverSide="Paper";
                if (clientSide.equalsIgnoreCase("Stone")) {
                    result="ServerWin";
                } else if (clientSide.equalsIgnoreCase("Scissors")) {
                    result="ClientWin";
                } else {
                    result="TwoDraw";
                }             
                break;
        }//end switch
        answer=serverSide+"#"+result; //返回服务器的选择和比赛结果
        return answer;
    }//end protocolWorking  
}// end Class