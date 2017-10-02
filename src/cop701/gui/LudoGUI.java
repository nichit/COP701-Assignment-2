package cop701.gui;
import javax.swing.*;

import cop701.common.GameState;

import java.awt.*;
import java.awt.Color;
public class LudoGUI extends JFrame implements Runnable
{
/**
	 * 
	 */
private static final long serialVersionUID = 1L;

private volatile GameState gs;

static JButton R[]=new JButton[]{new JButton("1"),new JButton("2"),new JButton("3"),new JButton("4")};
static JButton Y[]=new JButton[]{new JButton("1"),new JButton("2"),new JButton("3"),new JButton("4")};
static JButton G[]=new JButton[]{new JButton("1"),new JButton("2"),new JButton("3"),new JButton("4")};
static JButton B[]=new JButton[]{new JButton("1"),new JButton("2"),new JButton("3"),new JButton("4")};


static int x[]=new int[200];
static int y[]=new int[200];
static int x1[]={29,77,126,175,224,273,325,325,325,325,325,325,372,419,419,419,419,419,419,469,518,
		567,616,665,714,714,714,665,616,567,518,469,419,419,419,419,419,419,372,325,325,325,325,
		325,325,273,224,175,126,77,29,29};
static int x2[]={77,126,175,224,273,325,372,372,372,372,372,372,665,616,567,518,469,419,372,372,372,372,372,372};
/*static int y1[]={301,301,301,301,301,301,250,201,152,103,54,5,5,5,54,103,152,201,250,301,301,301,
		301,301,301,348,395,395,395,395,395,395,444,493,542,591,640,689,689,689,640,591,542,493,
		444,395,395,395,395,395,395,348};*/
static int y1[]={301,301,301,301,301,301,250,201,152,103,54,5,5,5,54,103,152,201,250,301,301,301,
		301,301,301,348,395,395,395,395,395,395,444,493,542,591,640,689,689,689,640,591,542,493,
		444,395,395,395,395,395,395,348};
static int y2[]={348,348,348,348,348,348,54,103,152,201,250,301,348,348,348,348,348,348,640,591,542,493,444,395};
static int homerx[]={100,160,100,160};
static int homery[]={100,100,160,160};
static int homeyx[]={550,610,550,610};
static int homeyy[]={550,550,610,610};
static int homegx[]={550,610,550,610};
static int homegy[]={100,100,160,160};
static int homebx[]={100,160,100,160};
static int homeby[]={550,550,610,610};
public LudoGUI()
    {
    setTitle("Ludo");
    setSize(800,800);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    R[3].setMargin(new Insets(0,0,0,0));
    //setLayout(new BorderLayout());
    setLayout(new FlowLayout(FlowLayout.CENTER));
    JLabel background=new JLabel(new ImageIcon(this.getClass().getResource("/resources/board.jpg")));
    add(background);
    background.setVisible(true);
    R[0].setBounds(100,100,35,35);
    R[0].setBackground(Color.RED);
    R[0].setMargin(new Insets(0,0,0,0));
    background.add(R[0]);
    R[1].setBounds(160,100,35,35);
    R[1].setBackground(Color.RED);
    R[1].setMargin(new Insets(0,0,0,0));
    background.add(R[1]);
    R[2].setBounds(100,160,35,35);
    R[2].setBackground(Color.RED);
    R[2].setMargin(new Insets(0,0,0,0));
    background.add(R[2]);
    R[3].setBounds(160,160,35,35);
    R[3].setBackground(Color.RED);
    R[3].setMargin(new Insets(0,0,0,0));
    background.add(R[3]);
    Y[0].setBounds(550,550,35,35);
    Y[0].setBackground(Color.YELLOW);
    Y[0].setMargin(new Insets(0,0,0,0));
    background.add(Y[0]);
    Y[1].setBounds(610,550,35,35);
    Y[1].setBackground(Color.YELLOW);
    Y[1].setMargin(new Insets(0,0,0,0));
    background.add(Y[1]);
    Y[2].setBounds(550,610,35,35);
    Y[2].setBackground(Color.YELLOW);
    Y[2].setMargin(new Insets(0,0,0,0));
    background.add(Y[2]);
    Y[3].setBounds(610,610,35,35);
    Y[3].setBackground(Color.YELLOW);
    Y[3].setMargin(new Insets(0,0,0,0));
    background.add(Y[3]);
    G[0].setBounds(550,100,35,35);
    G[0].setBackground(Color.GREEN);
    G[0].setMargin(new Insets(0,0,0,0));
    background.add(G[0]);
    G[1].setBounds(610,100,35,35);
    G[1].setBackground(Color.GREEN);
    G[1].setMargin(new Insets(0,0,0,0));
    background.add(G[1]);
    G[2].setBounds(550,160,35,35);
    G[2].setBackground(Color.GREEN);
    G[2].setMargin(new Insets(0,0,0,0));
    background.add(G[2]);
    G[3].setBounds(610,160,35,35);
    G[3].setBackground(Color.GREEN);
    G[3].setMargin(new Insets(0,0,0,0));
    background.add(G[3]);
    B[0].setBounds(100,550,35,35);
    B[0].setBackground(Color.BLUE);
    B[0].setMargin(new Insets(0,0,0,0));
    background.add(B[0]);
    B[1].setBounds(160,550,35,35);
    B[1].setBackground(Color.BLUE);
    B[1].setMargin(new Insets(0,0,0,0));
    background.add(B[1]);
    B[2].setBounds(100,610,35,35);
    B[2].setBackground(Color.BLUE);
    B[2].setMargin(new Insets(0,0,0,0));
    background.add(B[2]);
    B[3].setBounds(160,610,35,35);
    B[3].setBackground(Color.BLUE);
    B[3].setMargin(new Insets(0,0,0,0));
    background.add(B[3]);
    setVisible(true);
    for(int i=0;i<52;i++)
    {
    	x[i]=x1[i]-20;
    	y[i]=y1[i]+5;
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
    		button.setBounds(x[i],y[i],35,35);
    		try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
    
    public void run()
    {
    	Integer pieces[][]=gs.getPieces();
    	for(int i=0;i<2;i++)
    	{
    		for(int j=0;j<4;j++)
    			System.err.print(pieces[i][j]);
    		System.err.println();
    	}
    	if(gs.getColorMap().get(0)==cop701.common.Color.R)
    	{
    		for(int i=0;i<4;i++)
	    	{
				if(gs.getPieces()[0][i]==-1)
				{
					R[i].setBounds(homerx[i],homery[i],35,35);
				}
				else if(gs.getPieces()[0][i]==157)
				{
					R[i].setBounds(x2[5],y2[5],35,35);
				}
				else
				{
					if(gs.getBoard()[gs.getPieces()[0][i]].getNoOfPieces()[0]==1 && gs.getBoard()[gs.getPieces()[0][i]].getNoOfPieces()[1]==0)
						R[i].setBounds(x[gs.getPieces()[0][i]],y[gs.getPieces()[0][i]],35,35);
					else
					{
						R[i].setBounds(x[gs.getPieces()[0][i]],y[gs.getPieces()[0][i]]+((i+0)*2),35,35);
					}
				}
				if(gs.getPieces()[1][i]==-1)
				{
					Y[i].setBounds(homeyx[i],homeyy[i],35,35);
				}
				else if(gs.getPieces()[1][i]==131)
				{
					Y[i].setBounds(x2[17],y2[17],35,35);
				}
				else
				{
					if(gs.getBoard()[gs.getPieces()[1][i]].getNoOfPieces()[0]==0 && gs.getBoard()[gs.getPieces()[1][i]].getNoOfPieces()[1]==0)
						Y[i].setBounds(x[gs.getPieces()[1][i]],y[gs.getPieces()[1][i]],35,35);
					else
					{
						Y[i].setBounds(x[gs.getPieces()[1][i]]+((i+1)*2),y[gs.getPieces()[1][i]],35,35);
					}
				}
	    	}
    	}
    	else if(gs.getColorMap().get(0)==cop701.common.Color.G)
    	{
    		for(int i=0;i<4;i++)
	    	{
				if(gs.getPieces()[0][i]==-1)
				{
					G[i].setBounds(homegx[i],homegy[i],35,35);
				}
				else if(gs.getPieces()[0][i]==157)
				{
					G[i].setBounds(x2[11],y2[11],35,35);
				}
				else
				{
					if(gs.getBoard()[gs.getPieces()[0][i]].getNoOfPieces()[0]==0 && gs.getBoard()[gs.getPieces()[0][i]].getNoOfPieces()[1]==0)
						G[i].setBounds(x[(gs.getPieces()[0][i]+13)%52],y[(gs.getPieces()[0][i]+13)%52],35,35);
					else
					{
						G[i].setBounds(x[(gs.getPieces()[0][i]+13)%52],y[(gs.getPieces()[0][i]+13)%52]+((i+0)*2),35,35);
					}
				}
				if(gs.getPieces()[1][i]==-1)
				{
					B[i].setBounds(homebx[i],homeby[i],35,35);
				}
				else if(gs.getPieces()[1][i]==131)
				{
					B[i].setBounds(x2[23],y2[23],35,35);
				}
				else
				{
					if(gs.getBoard()[gs.getPieces()[1][i]].getNoOfPieces()[0]==0 && gs.getBoard()[gs.getPieces()[1][i]].getNoOfPieces()[1]==0)
						B[i].setBounds(x[(gs.getPieces()[1][i]+13)%52],y[(gs.getPieces()[1][i]+13)%52],35,35);
					else
					{
						B[i].setBounds(x[(gs.getPieces()[1][i]+13)%52]+((i+0)*2),y[(gs.getPieces()[1][i]+13)%52],35,35);
					}
				}
	    	}
    	}
    	else if(gs.getColorMap().get(0)==cop701.common.Color.Y)
    	{
    		for(int i=0;i<4;i++)
	    	{
				if(gs.getPieces()[0][i]==-1)
				{
					Y[i].setBounds(homeyx[i],homeyy[i],35,35);
				}
				else if(gs.getPieces()[0][i]==157)
				{
					Y[i].setBounds(x2[17],y2[17],35,35);
				}
				else
				{
					if(gs.getBoard()[gs.getPieces()[0][i]].getNoOfPieces()[0]==0 && gs.getBoard()[gs.getPieces()[0][i]].getNoOfPieces()[1]==0)
						Y[i].setBounds(x[(gs.getPieces()[0][i]+26)%52],y[(gs.getPieces()[0][i]+26)%52],35,35);
					else
					{
						Y[i].setBounds(x[(gs.getPieces()[0][i]+26)%52],y[(gs.getPieces()[0][i]+26)%52]+((i+0)*2),35,35);
					}
				}
				if(gs.getPieces()[1][i]==-1)
				{
					R[i].setBounds(homerx[i],homery[i],35,35);
				}
				else if(gs.getPieces()[1][i]==131)
				{
					R[i].setBounds(x2[5],y2[5],35,35);
				}
				else
				{
					if(gs.getBoard()[gs.getPieces()[1][i]].getNoOfPieces()[0]==0 && gs.getBoard()[gs.getPieces()[1][i]].getNoOfPieces()[1]==0)
						R[i].setBounds(x[(gs.getPieces()[1][i]+26)%52],y[(gs.getPieces()[1][i]+26)%52],35,35);
					else
					{
						R[i].setBounds(x[(gs.getPieces()[1][i]+26)%52]+((i+1)*2),y[(gs.getPieces()[1][i]+26)%52],35,35);
					}
				}
	    	}
    	}
    	else if(gs.getColorMap().get(0)==cop701.common.Color.B)
    	{
    		for(int i=0;i<4;i++)
	    	{
				if(gs.getPieces()[0][i]==-1)
				{
					B[i].setBounds(homebx[i],homeby[i],35,35);
				}
				else if(gs.getPieces()[0][i]==157)
				{
					B[i].setBounds(x2[23],y2[23],35,35);
				}
				else
				{
					if(gs.getBoard()[gs.getPieces()[0][i]].getNoOfPieces()[0]==0 && gs.getBoard()[gs.getPieces()[0][i]].getNoOfPieces()[1]==0)
						B[i].setBounds(x[(gs.getPieces()[0][i]+39)%52],y[(gs.getPieces()[0][i]+39)%52],35,35);
					else
					{
						B[i].setBounds(x[(gs.getPieces()[0][i]+39)%52],y[(gs.getPieces()[0][i]+39)%52]+((i+0)*2),35,35);
					}
				}
				if(gs.getPieces()[1][i]==-1)
				{
					G[i].setBounds(homegx[i],homegy[i],35,35);
				}
				else if(gs.getPieces()[1][i]==131)
				{
					G[i].setBounds(x2[11],y2[11],35,35);
				}
				else
				{
					if(gs.getBoard()[gs.getPieces()[1][i]].getNoOfPieces()[0]==0 && gs.getBoard()[gs.getPieces()[1][i]].getNoOfPieces()[1]==0)
						G[i].setBounds(x[(gs.getPieces()[1][i]+39)%52],y[(gs.getPieces()[1][i]+39)%52],35,35);
					else
					{
						G[i].setBounds(x[(gs.getPieces()[1][i]+39)%52]+((i+1)*2),y[(gs.getPieces()[1][i]+39)%52],35,35);
					}
				}
	    	}
    	}
    }
    
    public void changeState(GameState gs) {
    	setGameState(gs);
    	SwingUtilities.invokeLater(this);
    }
    
    private synchronized void setGameState(GameState gs) {
    	this.gs = gs;
    }
    
    public static void main(String args[])
    {
    	System.err.println(x.length+" "+y.length);
	    LudoGUI gui=new LudoGUI();
	    gui.testState(R[3]);
    }
} 