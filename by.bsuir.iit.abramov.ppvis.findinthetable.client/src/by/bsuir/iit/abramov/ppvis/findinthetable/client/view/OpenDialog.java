package by.bsuir.iit.abramov.ppvis.findinthetable.client.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.border.EtchedBorder;

import by.bsuir.iit.abramov.ppvis.findinthetable.client.controller.DialogCancelButtonActionListener;

public class OpenDialog extends JDialog {

	class ButtonOpenActionListener implements ActionListener {
		private final Desktop	desktop;
		private final JList		list;

		public ButtonOpenActionListener(final Desktop desktop, final JList list) {

			this.desktop = desktop;
			this.list = list;
		}

		@Override
		public void actionPerformed(final ActionEvent e) {

			desktop.loadFile(list.getSelectedValue());
		}

	}

	private final JPanel		contentPane		= new JPanel();
	private final JList			listFiles;
	public static final String	BUTTON_CANCEL	= "cancel";
	public static final String	BUTTON_OPEN		= "load";

	public static void showDialog(final Desktop desktop, final List<String> files) {

		try {
			final OpenDialog dialog = new OpenDialog(files, desktop);
			dialog.setVisible(true);
		} catch (final Exception e) {
			JOptionPane.showMessageDialog(null, "Не удалось открыть диалог");
		}
	}

	private final Desktop	desktop;

	/**
	 * Create the dialog.
	 */
	private OpenDialog(final List<String> files, final Desktop desktop) {

		this.desktop = desktop;
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		contentPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		getContentPane().add(contentPane, BorderLayout.CENTER);
		contentPane.setLayout(new BorderLayout(0, 0));
		listFiles = new JList(files.toArray());
		listFiles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		contentPane.add(listFiles, BorderLayout.CENTER);

		final JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton button = new JButton(Window.geti18nString(OpenDialog.BUTTON_OPEN));
		button.setActionCommand(OpenDialog.BUTTON_OPEN);
		button.addActionListener(new ButtonOpenActionListener(desktop, listFiles));
		buttonPane.add(button);

		button = new JButton(Window.geti18nString(OpenDialog.BUTTON_CANCEL));
		button.setActionCommand(OpenDialog.BUTTON_CANCEL);
		button.addActionListener(new DialogCancelButtonActionListener(this));
		buttonPane.add(button);

	}

	private int getSelectedIndex() {

		return listFiles.getSelectedIndex();
	}
}
