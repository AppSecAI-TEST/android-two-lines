package william.miranda.components.ui.two_lines;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

/**
 * Fragmento de duas linhas e um Switch
 */
public class TwoLinesSwitch extends TwoLinesDefaultFragment<Boolean> {

    CompoundButton checkbox;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_item_two_lines_switch, container, false);

        checkbox = (CompoundButton) view.findViewById(R.id.checkbox);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton button, boolean isChecked) {
                setValue(isChecked);
            }
        });
    }

    @Override
    public void setValue(Boolean value) {
        mValue = value;

        if (checkbox != null) {
            checkbox.setChecked(value);
        }
    }
}
