
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
/**
 *
 * @author omar
 */
public class ShowData extends JFrame {

    JTable table;
    JButton save;
    DataObject dataobject;
    ArrayList<String> exp;

    public ShowData(DataObject a, ArrayList<String> expression) {
        dataobject = a;
        exp = expression;
        setLayout(new GridLayout(2,1,1,1));
        int symSize = a.symbols.size();
        String[] col = new String[a.symbols.size() + expression.size()];
        String[][] row = new String[a.maskes.size()][symSize + expression.size()];
        for (int i = 0; i < symSize; i++) {
            col[i] = a.symbols.get(i) + "";
        }
        for (int i = 0; i < expression.size(); i++) {
            col[i + symSize] = expression.get(i) + "";
        }
        String t = "T";
        String f = "F";
        for (int i = 0; i < a.expValue.size(); i++) {
            boolean[] current = a.maskes.get(i);
            for (int j = a.symbols.size() - 1, k = 0; j > -1; k++, j--) {
                row[i][k] = current[j] ? t : f;
            }
            for (int j = 0; j < a.expValue.get(i).size(); j++) {
                row[i][j + symSize] = a.expValue.get(i).get(j) ? t : f;
            }
        }
        table = new JTable(row, col) {
            public boolean isCellEditable(int data, int columns) {
                return false;
            }

            public Component prepareRenderer(TableCellRenderer r, int row, int col) {
                Component c = super.prepareRenderer(r, row, col);
                if (row % 2 == 0) {
                    c.setBackground(Color.WHITE);
                } else {
                    c.setBackground(Color.LIGHT_GRAY);
                }
                if (isCellSelected(row, col)) {
                    c.setBackground(Color.GREEN);
                }
                return c;
            }
        };
        table.setFillsViewportHeight(true);
        table.setPreferredScrollableViewportSize(new Dimension(500, 200));
        JScrollPane jsp = new JScrollPane(table);
        save = new JButton("Save !");
        event ev = new event();
        save.addActionListener(ev);
        add(jsp);
        add(save);
    }

    public class event implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent a) {
            JFileChooser jfc = new JFileChooser();
            int ret = jfc.showSaveDialog(null);
            if (ret == JFileChooser.APPROVE_OPTION) {
                try {
                    String path = jfc.getSelectedFile() + ".txt";
                    printIntoFile(path);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void printIntoFile(String path) throws IOException {
        File f = new File(path);
        f.createNewFile();
        PrintWriter pw = new PrintWriter(new FileWriter(f));

        for (int i = 0; i < dataobject.symbols.size(); i++) {
            pw.print(dataobject.symbols.get(i) + " ");
        }
        for (int i = 0; i < exp.size(); i++) {
            pw.print(exp.get(i) + " ");
        }
        pw.println();
        for (int i = 0; i < dataobject.maskes.size(); i++) {
            boolean[] val = dataobject.maskes.get(i);
            for (int j = dataobject.symbols.size() - 1; j > -1; j--) {
                pw.print((val[j] ? 'T' : 'F') + " ");
            }
            for (int j = 0; j < exp.size(); j++) {
                pw.print(dataobject.expValue.get(i).get(j) ? 'T' : 'F');
                for (int k = 0; k < exp.get(j).length(); k++) {
                    pw.print(" ");
                }
            }
            pw.println();
        }
        pw.close();
    }
}
