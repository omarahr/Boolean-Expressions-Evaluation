
import java.io.*;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class MainFeatures {

    public static void oneExpressionAndFileSaveing(ArrayList<String> input) throws IOException {
        ArrayList<Validate> cases = new ArrayList<Validate>();
        for (int i = 0; i < input.size(); i++) {
            cases.add(new Validate(input.get(i)));
        }
        for (int i = 0; i < cases.size(); i++) {
            cases.get(i).replaceSigns();
        }
        boolean ch = true;
        for (int i = 0; i < cases.size(); i++) {
            if (!cases.get(i).check_string()) {
                ch = false;
            }
        }
        if (ch) {
            ArrayList<char[]> postfix = new ArrayList<char[]>();
            for (int i = 0; i < cases.size(); i++) {
                postfix.add(InfixToPostfix.change(cases.get(i).exp));
            }
            Evaluation testeval = new Evaluation(postfix);
            DataObject data = testeval.makeTruthTable();
            //
            JFrame newframe = new JFrame();
            ShowData data1 = new ShowData(data, input);
//            JScrollPane jsp = new JScrollPane(data1);
//            newframe.add(jsp);
            data1.setVisible(true);
            data1.setTitle("expression evluation");
            data1.setSize(500, 500);
            //
//            String path = input.get(0) + ".txt";
//            printIntoFile(data, path, input);
        } else {
            JOptionPane.showMessageDialog(null, "incorrect expression");
            System.out.println("FALSE");
        }
    }

    public static void printIntoFile(DataObject data, String path, ArrayList<String> expression) throws IOException {
        File file = new File(path);
        if (file.createNewFile()) {
            System.out.println("created");
        } else {
            System.out.println("exited");
        }
        PrintWriter pw = new PrintWriter(new FileWriter(path));
        for (int i = 0; i < data.symbols.size(); i++) {
            pw.print(data.symbols.get(i) + " ");
        }
        for (int i = 0; i < expression.size(); i++) {
            pw.print(expression.get(i) + " ");
        }
        pw.println();

        /*
         * print masks and value
         */
        for (int i = 0; i < data.expValue.size(); i++) {
            boolean[] current = data.maskes.get(i);
            for (int j = data.symbols.size() - 1; j > -1; j--) {
                pw.print((current[j] ? 1 : 0) + " ");
            }
            for (int j = 0; j < data.expValue.get(i).size(); j++) {
                pw.print(data.expValue.get(i).get(j) + " ");
            }
            pw.println();
        }
        pw.close();
    }

    public static void check_Tautology_Contradiction(String input) {
        Validate test = new Validate(input);
        test.replaceSigns();
        if (test.check_string()) {
            ArrayList<char[]> post = new ArrayList<char[]>();
            post.add(InfixToPostfix.change(test.exp));
            Evaluation testeval = new Evaluation(post);
            DataObject data = testeval.makeTruthTable();
            boolean t = true, c = true;
            for (int i = 0; i < data.expValue.size(); i++) {
                if (data.expValue.get(i).get(0)) {
                    c = false;
                } else {
                    t = false;
                }
            }
            if (t == c) {
                JOptionPane.showMessageDialog(null, "NONE");
                System.out.println("none");
            } else if (t) {
                JOptionPane.showMessageDialog(null, "tautology");
                System.out.println("tautology");
            } else {
                JOptionPane.showMessageDialog(null, "contradiction");
                System.out.println("contradiction");
            }
        } else {
            JOptionPane.showMessageDialog(null, "incorrect expression");
            System.out.println("FALSE");
        }
    }

    public static void check_Equvilance(ArrayList<String> input) {
        ArrayList<Validate> cases = new ArrayList<Validate>();
        for (int i = 0; i < input.size(); i++) {
            cases.add(new Validate(input.get(i)));
        }
        for (int i = 0; i < cases.size(); i++) {
            cases.get(i).replaceSigns();
        }
        boolean ch = true;
        for (int i = 0; i < cases.size(); i++) {
            if (!cases.get(i).check_string()) {
                ch = false;
            }
        }
        if (ch) {
            ArrayList<char[]> postfix = new ArrayList<char[]>();
            for (int i = 0; i < cases.size(); i++) {
                postfix.add(InfixToPostfix.change(cases.get(i).exp));
            }
            Evaluation testeval = new Evaluation(postfix);
            DataObject data = testeval.makeTruthTable();
            boolean check = true;
            for (int i = 0; i < data.expValue.size(); i++) {
                for (int j = 1; j < data.expValue.get(i).size(); j++) {
                    if (!data.expValue.get(i).get(j) == data.expValue.get(i).get(j - 1)) {
                        check = false;
                    }
                }
            }
            if (check) {
                JOptionPane.showMessageDialog(null, "the expressions are equal");
            } else {
                JOptionPane.showMessageDialog(null, "not equal");
            }
        } else {
            JOptionPane.showMessageDialog(null, "incorrect expression");
            System.out.println("FALSE");
        }





    }
}
