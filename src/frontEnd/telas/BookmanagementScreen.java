package frontEnd.telas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.*;

public class BookmanagementScreen extends JFrame {
    private JTable table;
    private DefaultTableModel model;

    public BookmanagementScreen() {
        setTitle("Gerenciamento de Livros");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);

        // Create table
        String[] columns = {"Título", "Autor", "Editora", "Capa Adicionada", "Imagem"};
        Object[][] data = {
            {"Livro 1", "Autor 1", "Editora 1", false, null},
            {"Livro 2", "Autor 2", "Editora 2", true, null},
            {"Livro 3", "Autor 3", "Editora 3", false, null},
            {"Livro 4", "Autor 4", "Editora 4", true, null},
            {"Livro 5", "Autor 5", "Editora 5", false, null}
        };
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
                    model.removeRow(rowInModel);
                    model.fireTableDataChanged();
                }
            }
        });

        // Add components to frame
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(removeBookButton);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BookmanagementScreen());
    }
}









