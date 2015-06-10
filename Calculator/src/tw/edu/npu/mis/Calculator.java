/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.edu.npu.mis;

/**
 * The model class of the calculator application.
 */
public class Calculator extends java.util.Observable{
    
    String Digit_F = "", Digit_L = "", Sort = "Front", Symbol = "", Percent_Digit = "", Memorize = "";
    boolean plus_minus = false, Memorize_recall = false;
    /**
     * The available operators of the calculator.
     */
    public enum Operator {
        CLEAR,       // C
        CLEAR_ENTRY, // CE
        BACKSPACE,   // ←
        EQUAL,       // =
        PLUS,        // +
        MINUS,       // -
        TIMES,       // ×
        OVER,        // ⁄
        PLUS_MINUS,  // ±
        RECIPROCAL,  // 1/x
        PERCENT,     // %
        SQRT,        // √
        MEM_CLEAR,   // MC
        MEM_SET,     // MS
        MEM_PLUS,    // M+
        MEM_MINUS,   // M-
        MEM_RECALL   // MR
    }
    
    public void appendDigit(int digit) {
        if(Sort == "Front") Digit_F += digit;
        else if(Sort == "Later") Digit_L += digit;
        
        this.setChanged();
        this.notifyObservers();
    }
    
    public void appendDot() {
        if(Sort == "Front") {
            if(Digit_F.indexOf(".") < 0) Digit_F = Digit_F + ".";
            else Digit_F = Digit_F;
        }
        else if(Sort == "Later") {
            if(Digit_L.indexOf(".") < 0) Digit_L = Digit_L + ".";
            else Digit_L = Digit_L;
        }
        
        this.setChanged();
        this.notifyObservers();
    }
    
    public void performOperation(Operator operator) {
        switch(operator) {
            case CLEAR:
                Sort = "Front";
                Symbol= "";
                Digit_F = "";
                Digit_L = "";
                break;
            case CLEAR_ENTRY:
                if(Sort == "Front") Digit_F = "";
                else if(Sort == "Later") Digit_L = "";
                break;
            case BACKSPACE:
                if(Sort == "Front" && Digit_F.length() > 0) Digit_F = Digit_F.substring(0, Digit_F.length() - 1);
                else if(Sort == "Later" && Digit_L.length() > 0) Digit_L = Digit_L.substring(0, Digit_L.length() - 1);
                break;
            case EQUAL:
                Sort = "Front";
                if(Digit_F != "" && Digit_L !="") equals();
                Symbol = "";
                break;
            case PLUS:
                Sort = "Later";
                if(Digit_L != "") equals();
                Symbol = "+";
                Percent_Digit = Digit_F;
                break;
            case MINUS:
                Sort = "Later";
                if(Digit_L != "") equals();
                Symbol = "-";
                Percent_Digit = Digit_F;
                break;
            case TIMES:               
                Sort = "Later";
                if(Digit_L != "") equals();
                Symbol = "*";
                Percent_Digit = Digit_F;
                break;
            case OVER:                
                Sort = "Later";
                if(Digit_L != "") equals();
                Symbol = "/";
                Percent_Digit = Digit_F;
                break;
            case PLUS_MINUS:               
                if(Sort == "Front") {
                    System.out.println(Digit_F.indexOf("-"));
                    if(Digit_F.indexOf("-") == -1) Digit_F = "-" + Digit_F;
                    else if(Digit_F.indexOf("-") == 0) Digit_F = Digit_F.replace("-", "");                    
                }
                else if(Sort == "Later") {
                     if(Digit_L.indexOf("-") < 0) Digit_L = "-" + Digit_L;
                     else if(Digit_L.indexOf("-") >= 0) Digit_L =Digit_L.replace("-", "");
                }              
                break;
            case RECIPROCAL:  // 1/x
                if(Sort == "Front") Digit_F = "" + (1.0/Double.valueOf(Digit_F));
                else if(Sort == "Later") Digit_L = "" + (1.0/Double.valueOf(Digit_L));
                break;
            case PERCENT:     // %
                if(Symbol != "") {
                    Digit_L = "" + Double.valueOf(Digit_F) * Double.valueOf(Percent_Digit) / 100.0;
                    Percent_Digit = Digit_L;
                    System.out.println(Digit_L);
                }
                break;
            case SQRT:        // √
                if(Sort == "Front" && !Digit_F.isEmpty() && Digit_F.indexOf("-") < 0) {
                    if(Digit_F.indexOf(".") > 0) Digit_F = "" + (Math.sqrt(Double.valueOf(Digit_F)));
                    else Digit_F = "" + (Math.sqrt(Integer.valueOf(Digit_F)));
                }
                else if(Sort == "Later" && !Digit_L.isEmpty() && Digit_L.indexOf("-") < 0) {
                    if(Digit_L.indexOf(".") > 0) Digit_L = "" + (Math.sqrt(Double.valueOf(Digit_L)));
                    else Digit_L = "" + (Math.sqrt(Integer.valueOf(Digit_L)));
                }
                break;
            case MEM_SET:
                if(Sort == "Front" && Digit_F != "") Memorize = Digit_F; 
                else if(Sort == "Later" && Digit_L != "") Memorize = Digit_L; 
                Memorize_recall = true;
                break;
            case MEM_RECALL:
                if(Memorize_recall) {
                    if(Sort == "Front") Digit_F = Memorize;
                    else if(Sort == "Later") Digit_L = Memorize;
                }
                break;
            case MEM_CLEAR:
                Memorize = "";
                Memorize_recall = false;
                break;
            case MEM_PLUS:
                if(Memorize_recall) {
                    if(Sort == "Front"&& !Digit_F.isEmpty()) {
                        Memorize = "" + (Double.valueOf(Memorize) + Double.valueOf(Digit_F));
                    }
                    else if(Sort == "Later") {
                        Memorize = "" + (Double.valueOf(Memorize) + Double.valueOf(Digit_L));
                    }
                }
                if(Memorize.indexOf(".0") == Memorize.length() - 2) Memorize = Memorize.replace(".0", "");
                break;
            case MEM_MINUS:
                if(Memorize_recall) {
                    if(Sort == "Front"&& !Digit_F.isEmpty()) {
                        Memorize = "" + (Double.valueOf(Memorize) - Double.valueOf(Digit_F));
                    }
                    else if(Sort == "Later") {
                        Memorize = "" + (Double.valueOf(Memorize) - Double.valueOf(Digit_L));
                    }
                }
                if(Memorize.indexOf(".0") == Memorize.length() - 2) Memorize = Memorize.replace(".0", "");
                break;            
        }
        this.setChanged();
        this.notifyObservers();
    }
    
    public String getDisplay() {
        if(Sort == "Front") return Digit_F;
        else if(Sort == "Later") return Digit_L;
        return null;
    }
    
    public void equals() {
        switch(Symbol) {
                    case "+":
                        Digit_F = "" + (Double.valueOf(Digit_F) + Double.valueOf(Digit_L));
                        break;
                    case "-":
                        Digit_F = "" + (Double.valueOf(Digit_F) - Double.valueOf(Digit_L));
                        break;
                    case "*":
                        Digit_F = "" + (Double.valueOf(Digit_F) * Double.valueOf(Digit_L));
                        break;
                    case "/":
                        //if(Digit_F.indexOf(".") > 0 || Digit_L.indexOf(".") > 0)
                            Digit_F = "" + (Double.valueOf(Digit_F) / Double.valueOf(Digit_L));
                        //else Digit_F = "" + (Integer.valueOf(Digit_F) / Integer.valueOf(Digit_L));                     
                        break;
                    }
        if(Digit_F.indexOf(".0") == Digit_F.length() - 2) Digit_F = Digit_F.replace(".0", "");
        Digit_L = "";
    }
    
    public static void main(String[] args) {
        Calculator model = new Calculator();
        Controller controller = new Controller(model);
        View view = new View(model, controller);
        view.setVisible(true);
        view.setSize(256, 305);
    }
}
