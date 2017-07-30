package william.miranda.components.ui.two_lines;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

/**
 * Fragmento de duas linhas e um SeekBar
 */
public class TwoLinesSeekBar extends TwoLinesDefaultFragment<Integer> {

    private SeekBar seekBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_item_two_lines_seekbar, container, false);
        seekBar = (SeekBar) view.findViewById(R.id.seekbar);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                mValue = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void setValue(Integer value) {
        mValue = value;

        if (seekBar != null) {
            seekBar.setProgress(value);
        }
    }
}
