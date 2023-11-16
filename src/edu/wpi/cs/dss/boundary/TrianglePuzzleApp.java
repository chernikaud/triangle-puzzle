package edu.wpi.cs.dss.boundary;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.wpi.cs.dss.adapter.MouseHandler;
import edu.wpi.cs.dss.controller.SwingController;
import edu.wpi.cs.dss.controller.impl.ResetController;
import edu.wpi.cs.dss.controller.impl.SwapController;
import edu.wpi.cs.dss.controller.impl.UnselectController;
import edu.wpi.cs.dss.domain.Model;
import edu.wpi.cs.dss.util.UpdateButtons;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;

public class TrianglePuzzleApp extends JFrame {
	
	private static final long serialVersionUID = 2766856238490959799L;
	
	private final Model model;
	private final TrianglePuzzlePanel trianglePuzzlePanel;

	private JButton resetButton;
	private JButton swapEdgesButton;
	private JButton unselectAllNodesButton;

	private JLabel scoreLabel;
	private JLabel movesLabel;

	public Model getModel() {
		return model;
	}

	public JButton getResetButton() {
		return resetButton;
	}

	public JButton getSwapEdgesButton() {
		return swapEdgesButton;
	}

	public JButton getUnselectAllNodesButton() {
		return unselectAllNodesButton;
	}

	public JLabel getScoreLabel() {
		return scoreLabel;
	}

	public JLabel getMovesLabel() {
		return movesLabel;
	}

	public TrianglePuzzleApp(Model model) {
		this.model = model;
		this.trianglePuzzlePanel = new TrianglePuzzlePanel(model);

		init();
	}

	private void init() {
		setTitle("Triangle Puzzle Application By Dmitriy Chernikov");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		trianglePuzzlePanel.addMouseListener(new MouseHandler(model, this));
		
		this.resetButton = new JButton("Reset");
		this.resetButton.addActionListener(e -> {
			final SwingController resetController = new ResetController(model, TrianglePuzzleApp.this);
			resetController.process();
		});

		this.unselectAllNodesButton = new JButton("Unselect all");
		this.unselectAllNodesButton.addActionListener(e -> {
			final SwingController unselectController = new UnselectController(model, TrianglePuzzleApp.this);
			unselectController.process();
		});

		this.swapEdgesButton = new JButton("Swap edges");
		this.swapEdgesButton.addActionListener(e -> {
			final SwingController swapController = new SwapController(model, TrianglePuzzleApp.this);
			swapController.process();
		});

		final JLabel lblScore = new JLabel("Score: ");
		this.scoreLabel = new JLabel("" + model.getScore());

		final JLabel lblMoves = new JLabel("Moves: ");
		this.movesLabel = new JLabel("" + model.getMoves());

		final GroupLayout groupLayoutContentPane = new GroupLayout(contentPane);
		groupLayoutContentPane.setHorizontalGroup(
				groupLayoutContentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayoutContentPane.createSequentialGroup()
								.addContainerGap()
								.addComponent(trianglePuzzlePanel, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addGroup(groupLayoutContentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(this.resetButton)
										.addComponent(this.unselectAllNodesButton)
										.addComponent(this.swapEdgesButton)
										.addGroup(groupLayoutContentPane.createSequentialGroup()
												.addGroup(groupLayoutContentPane.createParallelGroup(Alignment.LEADING)
														.addComponent(lblScore)
														.addComponent(lblMoves))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(groupLayoutContentPane.createParallelGroup(Alignment.LEADING)
														.addComponent(this.movesLabel)
														.addComponent(this.scoreLabel))))
								.addContainerGap(37, Short.MAX_VALUE))
		);
		groupLayoutContentPane.setVerticalGroup(
				groupLayoutContentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayoutContentPane.createSequentialGroup()
								.addContainerGap()
								.addGroup(groupLayoutContentPane.createParallelGroup(Alignment.TRAILING, false)
										.addGroup(groupLayoutContentPane.createSequentialGroup()
												.addGroup(groupLayoutContentPane.createParallelGroup(Alignment.BASELINE)
														.addComponent(lblScore)
														.addComponent(this.scoreLabel))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(groupLayoutContentPane.createParallelGroup(Alignment.BASELINE)
														.addComponent(lblMoves)
														.addComponent(this.movesLabel))
												.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(this.swapEdgesButton)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(this.unselectAllNodesButton)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(this.resetButton))
										.addComponent(trianglePuzzlePanel, GroupLayout.PREFERRED_SIZE, 520, GroupLayout.PREFERRED_SIZE))
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPane.setLayout(groupLayoutContentPane);

		UpdateButtons.updateButtons(this);
	}
}
