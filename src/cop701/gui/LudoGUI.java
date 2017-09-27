package cop701.gui;

import javax.swing.*;

import cop701.common.GameState;

import java.awt.*;
import java.awt.Color;
class LudoGUI extends JFrame
{
/**
	 * 
	 */
private static final long serialVersionUID = 1L;
static JButton R[]=new JButton[]{new JButton("R1"),new JButton("R2"),new JButton("R3"),new JButton("R4")};
static JButton Y[]=new JButton[]{new JButton("Y1"),new JButton("Y2"),new JButton("Y3"),new JButton("Y4")};

static int x[]=new int[200];
static int y[]=new int[200];
static int x1[]={29,77,126,175,224,273,325,325,325,325,325,325,372,419,419,419,419,419,419,469,518,
		567,616,665,714,714,714,665,616,567,518,469,419,419,419,419,419,419,372,325,325,325,325,
		325,325,273,224,175,126,77,29,29};
static int x2[]={77,126,175,224,273,325,372,372,372,372,372,372,665,616,567,518,469,419,372,372,372,372,372,372};
static int y1[]={301,301,301,301,301,301,250,201,152,103,54,5,5,5,54,103,152,201,250,301,301,301,
		301,301,301,348,395,395,395,395,395,395,444,493,542,591,640,689,689,689,640,591,542,493,
		444,395,395,395,395,395,395,348};
static int y2[]={348,348,348,348,348,348,54,103,152,201,250,301,348,348,348,348,348,348,640,591,542,493,444,395};
public LudoGUI()
    {
    setTitle("Background Color for JFrame");
    setSize(800,800);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
    setLayout(new BorderLayout());
    JLabel background=new JLabel(new ImageIcon("C:\\Users\\Admin\\Desktop\\Ludo\\board.jpg"));
    add(background);
    background.setVisible(true);
    R[0].setBounds(100,100,40,40);
    R[0].setBackground(Color.RED);
    background.add(R[0]);
    R[1].setBounds(160,100,40,40);
    R[1].setBackground(Color.RED);
    background.add(R[1]);
    R[2].setBounds(100,160,40,40);
    R[2].setBackground(Color.RED);
    background.add(R[2]);
    R[3].setBounds(325,250,40,40);
    R[3].setBackground(Color.RED);
    background.add(R[3]);
    Y[0].setBounds(550,550,40,40);
    Y[0].setBackground(Color.YELLOW);
    background.add(Y[0]);
    Y[1].setBounds(610,550,40,40);
    Y[1].setBackground(Color.YELLOW);
    background.add(Y[1]);
    Y[2].setBounds(550,610,40,40);
    Y[2].setBackground(Color.YELLOW);
    background.add(Y[2]);
    Y[3].setBounds(610,610,40,40);
    Y[3].setBackground(Color.YELLOW);
    background.add(Y[3]);
    
    for(int i=0;i<52;i++)
    {
    	x[i]=x1[i];
    	y[i]=y1[i];
    }
    for(int i=0;i<6;i++)
    {
    	x[i+152]=x2[i];
    	y[i+152]=y2[i];
    }
    for(int i=0;i<6;i++)
    {
    	x[i+113]=x2[i+6];
    	y[i+113]=y2[i+6];
    }
    for(int i=0;i<6;i++)
    {
    	x[i+126]=x1[i+12];
    	y[i+126]=y1[i+12];
    }
    for(int i=0;i<6;i++)
    {
    	x[i+139]=x1[i+18];
    	y[i+139]=y1[i+18];
    }
    
    }
    public void testState(JButton button)
    {
    	for(int i=0;i<52;i++)
    	{
    		button.setBounds(x[i],y[i],40,40);
    		try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
    
    public void changeState(GameState gs)
    {
    	for(int i=0;i<4;i++)
    	{
    		R[i].setBounds(x[gs.getPieces()[0][i]],y[gs.getPieces()[0][i]],40,40);
    		Y[i].setBounds(x[gs.getPieces()[1][i]],y[gs.getPieces()[1][i]],40,40);
    	}
    }
    public static void main(String args[])
    {
    	System.out.println(x.length+" "+y.length);
	    LudoGUI gui=new LudoGUI();
	    gui.testState(R[3]);
    }
} 
