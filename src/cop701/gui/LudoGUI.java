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
	
	static JButton R[]=new JButton[]{new JButton("R1"),new JButton("R2"),new JButton("R3"),new JButton("R4")};
	static JButton Y[]=new JButton[]{new JButton("Y1"),new JButton("Y2"),new JButton("Y3"),new JButton("Y4")};
	static JButton G[]=new JButton[]{new JButton("G1"),new JButton("G2"),new JButton("G3"),new JButton("G4")};
	static JButton B[]=new JButton[]{new JButton("B1"),new JButton("B2"),new JButton("B3"),new JButton("B4")};
	
	
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
    
    setLayout(new BorderLayout());
    JLabel background=new JLabel(new ImageIcon(this.getClass().getResource("/resources/board.jpg")));
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
    R[3].setBounds(160,160,40,40);
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
    G[0].setBounds(550,100,40,40);
    G[0].setBackground(Color.GREEN);
    background.add(G[0]);
    G[1].setBounds(610,100,40,40);
    G[1].setBackground(Color.GREEN);
    background.add(G[1]);
    G[2].setBounds(550,160,40,40);
    G[2].setBackground(Color.GREEN);
    background.add(G[2]);
    G[3].setBounds(610,160,40,40);
    G[3].setBackground(Color.GREEN);
    background.add(G[3]);
    B[0].setBounds(100,550,40,40);
    B[0].setBackground(Color.BLUE);
    background.add(B[0]);
    B[1].setBounds(160,550,40,40);
    B[1].setBackground(Color.BLUE);
    background.add(B[1]);
    B[2].setBounds(100,610,40,40);
    B[2].setBackground(Color.BLUE);
    background.add(B[2]);
    B[3].setBounds(160,610,40,40);
    B[3].setBackground(Color.BLUE);
    background.add(B[3]);
    setVisible(true);
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
					R[i].setBounds(homerx[i],homery[i],40,40);
				}
				else if(gs.getPieces()[0][i]==157)
				{
					R[i].setBounds(x2[5],y2[5],40,40);
				}
				else
				{
					R[i].setBounds(x[gs.getPieces()[0][i]],y[gs.getPieces()[0][i]],40,40);
				}
				if(gs.getPieces()[1][i]==-1)
				{
					Y[i].setBounds(homeyx[i],homeyy[i],40,40);
				}
				else if(gs.getPieces()[1][i]==131)
				{
					Y[i].setBounds(x2[17],y2[17],40,40);
				}
				else
				{
					Y[i].setBounds(x[gs.getPieces()[1][i]],y[gs.getPieces()[1][i]],40,40);
				}
	    	}
    	}
    	else if(gs.getColorMap().get(0)==cop701.common.Color.G)
    	{
    		for(int i=0;i<4;i++)
	    	{
				if(gs.getPieces()[0][i]==-1)
				{
					G[i].setBounds(homegx[i],homegy[i],40,40);
				}
				else if(gs.getPieces()[0][i]==157)
				{
					G[i].setBounds(x2[11],y2[11],40,40);
				}
				else
				{
					G[i].setBounds(x[(gs.getPieces()[0][i]+13)%52],y[(gs.getPieces()[0][i]+13)%52],40,40);
				}
				if(gs.getPieces()[1][i]==-1)
				{
					B[i].setBounds(homebx[i],homeby[i],40,40);
				}
				else if(gs.getPieces()[1][i]==131)
				{
					B[i].setBounds(x2[23],y2[23],40,40);
				}
				else
				{
					B[i].setBounds(x[(gs.getPieces()[1][i]+13)%52],y[(gs.getPieces()[1][i]+13)%52],40,40);
				}
	    	}
    	}
    	else if(gs.getColorMap().get(0)==cop701.common.Color.Y)
    	{
    		for(int i=0;i<4;i++)
	    	{
				if(gs.getPieces()[0][i]==-1)
				{
					Y[i].setBounds(homeyx[i],homeyy[i],40,40);
				}
				else if(gs.getPieces()[0][i]==157)
				{
					Y[i].setBounds(x2[17],y2[17],40,40);
				}
				else
				{
					Y[i].setBounds(x[(gs.getPieces()[0][i]+26)%52],y[(gs.getPieces()[0][i]+26)%52],40,40);
				}
				if(gs.getPieces()[1][i]==-1)
				{
					R[i].setBounds(homerx[i],homery[i],40,40);
				}
				else if(gs.getPieces()[1][i]==131)
				{
					R[i].setBounds(x2[5],y2[5],40,40);
				}
				else
				{
					R[i].setBounds(x[gs.getPieces()[1][i]],y[gs.getPieces()[1][i]],40,40);
				}
	    	}
    	}
    	else if(gs.getColorMap().get(0)==cop701.common.Color.B)
    	{
    		for(int i=0;i<4;i++)
	    	{
				if(gs.getPieces()[0][i]==-1)
				{
					B[i].setBounds(homebx[i],homeby[i],40,40);
				}
				else if(gs.getPieces()[0][i]==157)
				{
					B[i].setBounds(x2[23],y2[23],40,40);
				}
				else
				{
					B[i].setBounds(x[(gs.getPieces()[0][i]+39)%52],y[(gs.getPieces()[0][i]+39)%52],40,40);
				}
				if(gs.getPieces()[1][i]==-1)
				{
					G[i].setBounds(homegx[i],homegy[i],40,40);
				}
				else if(gs.getPieces()[1][i]==131)
				{
					G[i].setBounds(x2[11],y2[11],40,40);
				}
				else
				{
					G[i].setBounds(x[(gs.getPieces()[1][i]+39)%52],y[(gs.getPieces()[1][i]+39)%52],40,40);
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
