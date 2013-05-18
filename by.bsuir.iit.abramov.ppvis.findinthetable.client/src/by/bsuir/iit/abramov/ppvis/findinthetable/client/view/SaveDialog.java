package by.bsuir.iit.abramov.ppvis.findinthetable.client.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import by.bsuir.iit.abramov.ppvis.findinthetable.client.controller.DialogCancelButtonActionListener;
import by.bsuir.iit.abramov.ppvis.findinthetable.client.util.Util;

public class SaveDialog extends JDialog {
	class ButtonSaveActionListener implements ActionListener {
		private final Desktop	desktop;
		private final JComboBox		comboBox;
		private final JDialog dialog;

		public ButtonSaveActionListener(final JDialog dialog, final Desktop desktop, final JComboBox comboBox) {

			this.dialog = dialog;
			this.desktop = desktop;
			this.comboBox = comboBox;
		}

		@Override
		public void actionPerformed(final ActionEvent e) {

			if (isComboBoxEmpty(comboBox) || isComboBoxIncorrect(comboBox)) {
				JOptionPane.showMessageDialog(null, "Incorrect file name");
				return;
			}
			desktop.saveFile(comboBox.getSelectedItem());
			dialog.setVisible(false);
			dialog.dispose();
		}

	}
	

	private boolean isComboBoxIncorrect(final JComboBox comboBox) {

		return ((String) comboBox.getSelectedItem()).length() == 0;
	}

	private boolean isComboBoxEmpty(final JComboBox comboBox) {

		if (comboBox.getSelectedIndex() == -1
				&& comboBox.getSelectedItem() == null) {
			return true;
		}
		return ((String) comboBox.getSelectedItem()).length() == 0;
	}
	
	public static void showDialog(final Desktop desktop, final List<String> files) {

		try {
			final SaveDialog dialog = new SaveDialog(files, desktop);
			dialog.setVisible(true);
		} catch (final Exception e) {
			JOptionPane.showMessageDialog(null, "Не удалось открыть диалог");
		}
	}

	private final JPanel	contentPane	= new JPanel();
	private final JComboBox comboBox;
	public static final String BUTTON_CANCEL = "cancel";
	public static final String BUTTON_SAVE = "save";

	/**
	 * Create the dialog.
	 */
	private SaveDialog(final List<String> files, final Desktop desktop) {

		setBounds(100, 100, 334, 146);
		getContentPane().setLayout(new BorderLayout());
		contentPane.setLayout(new FlowLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPane, BorderLayout.CENTER);

		final JLabel lblChooseName = new JLabel("Choose name");
		contentPane.add(lblChooseName);

		comboBox = new JComboBox(files.toArray());
		comboBox.setEditable(true);
		contentPane.add(comboBox);

		final JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton button = new JButton(Window.geti18nString(BUTTON_SAVE));
		button.setActionCommand(BUTTON_SAVE);
		button.addActionListener(new ButtonSaveActionListener(this, desktop, comboBox));
		buttonPane.add(button);
		
		getRootPane().setDefaultButton(button);

		button = new JButton(Window.geti18nString(BUTTON_CANCEL));
		button.setActionCommand(BUTTON_CANCEL);
		button.addActionListener(new DialogCancelButtonActionListener(this));
		buttonPane.add(button);
		

	}

}
