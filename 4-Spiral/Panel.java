import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

class Panel extends JPanel implements ChangeListener{

 // 	ColorPanel cPanel;
	JSlider lambdaSlider, rowcolSlider;
  JToggleButton toggle;
	JLabel rlabel1,rlabel2,rlabel3;
	HashMap state;
	Canvas myCanvas;

 	public Panel(Canvas _myCanvas){

		myCanvas = _myCanvas;

		setLayout(new GridLayout(1,3,30,10));

		state = new HashMap();
		state.put("lambda",(float)0.05);
		state.put("mode","SINGLE");
		state.put("rowcol","ROW");
		state.put("rowcolVal",1);


    lambdaSlider = new JSlider(JSlider.HORIZONTAL,5,95,5);
		lambdaSlider.setMajorTickSpacing(10);
		lambdaSlider.setMinorTickSpacing(5);

		Hashtable lambdaValStrings = new Hashtable();
		int label_val = 0;
		while (label_val < 91)lambdaValStrings.put( new Integer( label_val+=10 ),
			new JLabel(String.format("%.1f",(float)label_val/100)));
		lambdaSlider.setLabelTable( lambdaValStrings );

		rlabel1 = new JLabel("Choose Lambda:");
		rlabel2 = new JLabel("Choose mode:");
		rlabel3 = new JLabel("Choose row or col N:");

		JPanel r = new JPanel();
		r.setLayout(new BoxLayout(r, BoxLayout.Y_AXIS));

		toggle = new JToggleButton("SINGLE");
		toggle.addChangeListener(new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent event) {
					String newVal = toggle.isSelected() ? "PATTERN" : "SINGLE";
					state.put("mode",newVal);
					updateStateGUI();
        }
    });

		rowcolSlider = new JSlider(JSlider.HORIZONTAL,1,31,1);
		rowcolSlider.setMajorTickSpacing(5);
		rowcolSlider.setMinorTickSpacing(1);
		for (JSlider slider: new JSlider[] {lambdaSlider,rowcolSlider}){
			slider.setPaintTicks(true);
			slider.setPaintLabels(true);
			slider.addChangeListener(this);
		}

		for(JComponent component: new JComponent[]{rlabel1,lambdaSlider,rlabel2,
			toggle,rlabel3,rowcolSlider})r.add(component);
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
			state.put("rowcolVal",1);
			rowcolSlider.setValue(1);
		}else{
			rlabel3.setVisible(true);
			rowcolSlider.setVisible(true);
		}
		toggle.setText(mode);
		myCanvas.update(state);
	}
}
