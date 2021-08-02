

/**
 * @author kkwang
 * @create 2021-07-19 20:36
 */
package UI;

import util.ParameterTracker;

import javax.swing.*;
import java.awt.*;
public abstract class DialogStub extends JDialog {

    protected ParameterTracker params;
    protected int result = -1;
    public DialogStub(ParameterTracker params, Window owner,String title){
        super(owner,title);
        this.params = params;
        this.setDefaultCloseOperation(2);

    }

    public int showAndGetResults(){
        this.setLayout(new GridBagLayout());
        this.setResizable(false);
        this.layoutComponents();
        this.pack();
        this.setVisible(true);
        return this.result;
    }

    protected abstract void layoutComponents();



}
