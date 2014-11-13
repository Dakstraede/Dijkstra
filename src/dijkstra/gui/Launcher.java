package dijkstra.gui;
import dijkstra.controlers.SimulControler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Launcher extends JFrame{

    private SimulControler controler;

    public Launcher(final SimulControler controler)
    {
        this.controler = controler;

        setPreferredSize(new Dimension(300,300));
        setTitle("Dijkstra Simulation");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3,1));

        JButton quick = new JButton("Lauch quick simulation");
        JButton full = new JButton("Lauch full simulation");
        JButton quitApp = new JButton("Quit");

        add(quick);
        add(full);
        add(quitApp);

        quick.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Do the work with default map
                //controller

            }
        });

        full.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //File selection, check and do the work
                //controller
            }
        });

        quitApp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        pack();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setLocation((screenSize.width - getWidth()) /2, (screenSize.height - getHeight()) / 2);
        setVisible(true);
    }
}
