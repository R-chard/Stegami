/**
		 * The "Save" menu item allows users to save the current file to be opened later
		 * on. Files are saved with a custom file extension type so it is not
		 * accidentally opened by other programs.
		 */
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					JFileChooser chooser = new JFileChooser();
					chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					chooser.setCurrentDirectory(new File("."));

					String fileName = "";

					// Manually creating the full path of the file
					if (chooser.showSaveDialog(null) == chooser.APPROVE_OPTION) {
						fileName = chooser.getSelectedFile().getAbsolutePath() + "." + FILE_EXTENSION;

						// Passing file path to the FileOutputStream and subsequently the
						// ObjectOutputStream so that it can be serialised and written to file.
						FileOutputStream fileOutputStream = new FileOutputStream(fileName);
						ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
						objectOutputStream.writeObject(model.getShapes());
						objectOutputStream.close();
					}

					// Informing the user the file has been successfully saved
					JOptionPane.showMessageDialog(menuBar, "File saved successfully", "File saved",
							JOptionPane.PLAIN_MESSAGE);

				} catch (Exception err) {
					JOptionPane.showMessageDialog(menuBar, "An error occured while saving file", "Error",
							JOptionPane.PLAIN_MESSAGE);
					err.printStackTrace();
				}

			}
		});