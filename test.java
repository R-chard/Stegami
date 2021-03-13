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
		}) 

		/**
		 * The "Load" menu item allows users to import a file that has been previously
		 * saved
		 */
		load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileFilter(new FileNameExtensionFilter("." + FILE_EXTENSION, FILE_EXTENSION));
				chooser.setCurrentDirectory(new File("."));
				chooser.setDialogTitle("Open File");
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

				int response = chooser.showOpenDialog(null);
				if (response == JFileChooser.APPROVE_OPTION) {
					try {

						// Retrieving data saved in file through input streams before data is
						// unmarshalled to an object
						File file = chooser.getSelectedFile();
						FileInputStream fileInputStream = new FileInputStream(file);
						ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
						Object storedObject = objectInputStream.readObject();

						// checks for object type to ensure safe type-casting
						if (storedObject instanceof Stack) {
							model.setShapes((Stack<AbstractShape>) storedObject);
							objectInputStream.close();

						} else {
							objectInputStream.close();
							throw new IllegalArgumentException();
						}

					} catch (Exception err) {
						// Exception is displayed as a JOptionPane to the user
						JOptionPane.showMessageDialog(menuBar, "An error occured while loading file", "Error",
								JOptionPane.PLAIN_MESSAGE);
						err.printStackTrace();
					}

				}
			}
		});

	
        