package PW;

import PW.PW.utils.GameUtils;
import PW.obj.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class GameWin extends JFrame {
    //游戏状态 0未开始 1游戏中 2暂停 3通关失败 4通关成功
    //设置游戏状态为0，状态使用state表示
    public static int state = 0;
    int enemyCount = 0 ;
    Image offScreenImage = null;
    //游戏窗口长、宽，设置为600X600
    int width = 600;
    int height =600;
    //设置背景
    int count = 1;
    public static int score = 0 ;
    BgObj bjObj = new BgObj(GameUtils.bgImg,0,-2000,2);
    //设置飞机，己方
    public PlaneObj planeObj =new PlaneObj(GameUtils.planeImg,290,550,20,30,0,this);
    //设置子弹
    //ShellObj shellObj =new ShellObj(GameUtils.shellImg,planeObj.getX() + 3,planeObj.getY()-16,14,29,5,this);
    public BossObj bossObj =null;
    //设置窗口
    public void launch(){
        this.setVisible(true);//窗口可见
        this.setSize(width, height);//窗口尺寸大小
        this.setLocationRelativeTo(null);//设置窗口位置，null中央
        this.setTitle("ACDTGAME");//设置标题

        GameUtils.gameObjList.add(bjObj);
        GameUtils.gameObjList.add(planeObj);
        //GameUtils.gameObjList.add(bossObj);
        //鼠标点击事件
        this.addMouseListener(new MouseAdapter() {
            //e.getButton() == 1左键点击
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == 1 && state == 0){
                    state = 1;
                    repaint();
                }
            }
        });
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==32){
                    switch (state){
                        case 1:
                            state =2;
                            break;
                        case 2:
                            state = 1;
                            break;
                        default:
                    }
                }
            }
        });
        while (true){
            if(state==1){
                craeteObj();
                repaint();
            }

            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        if(offScreenImage==null){
            offScreenImage =createImage(width,height);
        }
        Graphics gImage = offScreenImage.getGraphics();
        gImage.fillRect(0,0,width,height);
        if (state == 0) {
            gImage.drawImage(GameUtils.bgImg, 0, 0, null);
            gImage.drawImage(GameUtils.bossImg, 220, 120, null);
            gImage.drawImage(GameUtils.explodeImg, 270, 350, null);
            GameUtils.drawWord(gImage,"点击开始游戏",Color.yellow,40,180,300);
//            gImage.setColor(Color.yellow);
//            gImage.setFont(new Font("仿宋", Font.BOLD, 40));
//            gImage.drawString("点击开始游戏", 180, 300);
        }
        if (state == 1) {
            GameUtils.gameObjList.addAll(GameUtils.explodeObjList);
//            bjObj.paintSelf(gImage);
//            planeObj.paintSelf(gImage);
            //shellObj.paintSelf(gImage);
            for (int i = 0; i < GameUtils.gameObjList.size(); i++) {
                GameUtils.gameObjList.get(i).paintSelf(gImage);
            }
            if (state == 2) {

            }
            if (state == 3) {
                gImage.drawImage(GameUtils.explodeImg, planeObj.getX()-35, planeObj.getY()-50, null);
                GameUtils.drawWord(gImage,"你输了",Color.RED,40,180,300);
//                gImage.setColor(Color.RED);
//                gImage.setFont(new Font("仿宋", Font.BOLD, 50));
//                gImage.drawString("游戏结束！", 180, 300);
            }
            if (state == 4) {
                gImage.drawImage(GameUtils.explodeImg, bossObj.getX()+30, bossObj.getY(), null);
                GameUtils.drawWord(gImage,"你赢了",Color.GREEN,40,180,300);
//                gImage.setColor(Color.RED);
//                gImage.setFont(new Font("仿宋", Font.BOLD, 50));
//                gImage.drawString("游戏结束！", 180, 300);
            }
            GameUtils.drawWord(gImage,score+"分",Color.green,40,30,100);
            GameUtils.gameObjList.removeAll(GameUtils.removeObjList);
        }
        g.drawImage(offScreenImage,0,0,null);
        count++;
    }

    void craeteObj(){
        if (count % 10 ==0){
            GameUtils.shellObjList.add(new ShellObj(GameUtils.shellImg,planeObj.getX() + 3,planeObj.getY()-16,14,29,5,this));
            GameUtils.gameObjList.add(GameUtils.shellObjList.get(GameUtils.shellObjList.size()-1));
        }
        if (count % 50 ==0 ){
            GameUtils.enemyObjList.add(new EnemyObj(GameUtils.enemyImg,(int)(Math.random()*12)*50,0,49,36,3,this));
            GameUtils.gameObjList.add(GameUtils.enemyObjList.get(GameUtils.enemyObjList.size()-1));
            enemyCount++;
        }
        if (count % 10 ==0 &&bossObj !=null){
            GameUtils.bullObjList.add(new BullObj(GameUtils.bullImg,bossObj.getX()+76,bossObj.getY()+85,15,25,5,this));
            GameUtils.gameObjList.add(GameUtils.bullObjList.get(GameUtils.bullObjList.size()-1));
        }
        if(enemyCount>10 &&bossObj ==null){
            bossObj =new BossObj(GameUtils.bossImg,250,50,155,100,5,this);
            GameUtils.gameObjList.add(bossObj);
        }
    }

    public static void main(String[] args){
        GameWin gamewin = new GameWin();
        gamewin.launch();
    }
}