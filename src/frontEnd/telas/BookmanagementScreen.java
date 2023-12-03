package frontEnd.telas;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.*;

import backEnd.User;

public class BookmanagementScreen extends JFrame {
    private JTable table;
    private DefaultTableModel model;

    
    public static void main(String[] args, User user) {
        SwingUtilities.invokeLater(() -> {
            try {
                new BookmanagementScreen(backEnd.CoverHandler.getNonPublicBooks(), user);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }



    public BookmanagementScreen(Object[][] data, User user) {
        setTitle("Gerenciamento de Livros");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);

        // Create table
        String[] columns = {"Título", "Autor", "Editora", "Capa Adicionada", "Imagem"};
        model = new DefaultTableModel(data, columns) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 3 ? Boolean.class : columnIndex == 4 ? ImageIcon.class : Object.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4; // Allow editing only for the "Imagem" column
            }
        };
        table = new JTable(model);

        // Add image renderer for the "Imagem" column
        table.getColumnModel().getColumn(4).setCellRenderer(new ImageRenderer());

        // Add image editor for the "Imagem" column
        table.getColumnModel().getColumn(4).setCellEditor(new ImageEditor());

        // Add buttons
        JButton addButton = new JButton("Adicionar Capa");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int rowInView = table.getSelectedRow();
                int rowInModel = table.convertRowIndexToModel(rowInView);

                if (rowInModel != -1) {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setFileFilter(new FileNameExtensionFilter("Imagens", "jpg", "png", "jpeg"));

                    int result = fileChooser.showOpenDialog(BookmanagementScreen.this);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        String imagePath = fileChooser.getSelectedFile().getAbsolutePath();
                        model.setValueAt(true, rowInModel, 3);
                        model.setValueAt(new ImageIcon(imagePath), rowInModel, 4);
                        model.fireTableDataChanged();
                        try {
                            backEnd.CoverHandler.updateBookCover(imagePath, model.getValueAt(rowInModel,0).toString());
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                        dispose();
                        frontEnd.telas.BookmanagementScreen.main(null, user);
                    }
                }
            }
        });

        JButton removeButton = new JButton("Remover Capa");
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int rowInView = table.getSelectedRow();
                int rowInModel = table.convertRowIndexToModel(rowInView);

                if (rowInModel != -1) {
                    model.setValueAt(false, rowInModel, 3);
                    model.setValueAt(null, rowInModel, 4);
                    model.fireTableDataChanged();
                }
            }
        });

        JButton removeBookButton = new JButton("Remover Livro");
        removeBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int rowInView = table.getSelectedRow();
                int rowInModel = table.convertRowIndexToModel(rowInView);

                if (rowInModel != -1) {
                    backEnd.Services.removeBook(model.getValueAt(rowInModel,0).toString());
                    dispose();
                    frontEnd.telas.BookmanagementScreen.main(null, user);
                }
            }
        });

        JButton publishBookButton = new JButton("Publicar Livro");
        publishBookButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int rowInView = table.getSelectedRow();
                int rowInModel = table.convertRowIndexToModel(rowInView);
                backEnd.BookHandler.publishBook(model.getValueAt(rowInModel,0).toString());
                dispose();
                frontEnd.telas.BookmanagementScreen.main(null, user);
            }
        });
        JButton returnButton = new JButton("Voltar para o Menu");
        returnButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispose();
                frontEnd.telas.AdminMenuScreen.main(null, user);
            }
        });

        // Add components to frame
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(removeBookButton);
        buttonPanel.add(publishBookButton);
        buttonPanel.add(returnButton);
        add(buttonPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        setVisible(true);
    }

    // TableCellRenderer for rendering images
    class ImageRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel label = new JLabel();
            if (value instanceof ImageIcon) {
                label.setIcon((ImageIcon) value);
            }
            label.setHorizontalAlignment(SwingConstants.CENTER);
            return label;
        }
    }

    // Custom TableCellEditor for opening images
    class ImageEditor extends AbstractCellEditor implements TableCellEditor {
        private JButton button;
        private ImageIcon image;

        public ImageEditor() {
            button = new JButton();
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (image != null) {
                        ImageIconViewer viewer = new ImageIconViewer(image);
                        viewer.setVisible(true);
                    }
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            if (value instanceof ImageIcon) {
                image = (ImageIcon) value;
                button.setIcon(image);
            } else {
                image = null;
                button.setIcon(null);
            }
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return image;
        }
    }

    // Simple JFrame for displaying ImageIcon in a new window
    class ImageIconViewer extends JFrame {
        public ImageIconViewer(ImageIcon image) {
            setTitle("Visualizar Imagem");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setSize(400, 400);
            JLabel label = new JLabel(image);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            add(label);
        }
    }

}







