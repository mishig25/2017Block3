import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

class Panel extends JPanel implements ChangeListener{

 // 	ColorPanel cPanel;
	JSlider lambdaSlider, rowcolSlider;
  JToggleButton toggle;
	JToggleButton rowcol;
	JLabel rlabel3;
	HashMap state;
	Canvas myCanvas;

 	public Panel(Canvas _myCanvas){

		myCanvas = _myCanvas;

		setLayout(new GridLayout(1,3,30,10));

		state = new HashMap();
		state.put("lambda",(float)0.1);
		state.put("mode","SINGLE");
		state.put("rowcol","ROW");
		state.put("rowcolVal",1);


    lambdaSlider = new JSlider(JSlider.HORIZONTAL,10,90,10);
		lambdaSlider.setMajorTickSpacing(10);
		lambdaSlider.setMinorTickSpacing(5);
		lambdaSlider.setPaintTicks(true);
		lambdaSlider.setPaintLabels(true);
		lambdaSlider.addChangeListener(this);
		Hashtable lambdaValStrings = new Hashtable();
		int label_val = 0;
		while (label_val < 91)lambdaValStrings.put( new Integer( label_val+=10 ), new JLabel(String.format("%.1f",(float)label_val/100)));
		lambdaSlider.setLabelTable( lambdaValStrings );

		JLabel rlabel = new JLabel("Choose Lambda:");
		JPanel r = new JPanel();
		r.setLayout(new BoxLayout(r, BoxLayout.Y_AXIS));
		r.add(rlabel);
    r.add(lambdaSlider);
		JLabel rlabel2 = new JLabel("Choose mode:");
		r.add(rlabel2);
		toggle = new JToggleButton("SINGLE");
		toggle.addChangeListener(new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent event) {
					String newVal = toggle.isSelected() ? "PATTERN" : "SINGLE";
					state.put("mode",newVal);
					updateStateGUI();
        }
    });
		r.add(toggle);

		rlabel3 = new JLabel("Choose row or col:");
		r.add(rlabel3);
		rowcol = new JToggleButton("ROW");
		rowcol.addChangeListener(new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent event) {
					String newVal = rowcol.isSelected() ? "COL" : "ROW";
					state.put("rowcol",newVal);
					updateStateGUI();
        }
    });
		r.add(rowcol);

		rowcolSlider = new JSlider(JSlider.HORIZONTAL,1,31,1);
		rowcolSlider.setMajorTickSpacing(5);
		rowcolSlider.setMinorTickSpacing(1);
		rowcolSlider.setPaintTicks(true);
		rowcolSlider.setPaintLabels(true);
		rowcolSlider.addChangeListener(this);
		r.add(rowcolSlider);


		add(r);

		updateStateGUI();

  }//end contructor

  public void stateChanged(ChangeEvent ev){
    // get slider vals
		float lambdaVal = (float)lambdaSlider.getValue();
		lambdaVal /= 100;
		int rowcolVal = rowcolSlider.getValue();
		state.put("lambda",lambdaVal);
		state.put("rowcolVal",rowcolVal);
		updateStateGUI();
	}//end stateChanged

	public void updateStateGUI(){
		// parse HashMap vals
		String mode = (String)state.get("mode");
		char[] arr = mode.toCharArray();
		if(arr[0] == 'S'){
			rlabel3.setVisible(false);
			rowcolSlider.setVisible(false);
			rowcol.setVisible(false);
		}else{
			rlabel3.setVisible(true);
			rowcolSlider.setVisible(true);
			rowcol.setVisible(true);
		}
		toggle.setText(mode);
		rowcol.setText((String)state.get("rowcol"));
		myCanvas.update(state);
	}

}
