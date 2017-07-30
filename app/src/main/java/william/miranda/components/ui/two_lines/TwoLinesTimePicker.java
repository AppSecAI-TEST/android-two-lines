package william.miranda.components.ui.two_lines;

import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Fragmento de duas linhas que mostra um TimePicker quando Clicado
 */
public class TwoLinesTimePicker extends TwoLinesDefaultFragment<Calendar> {

    private SimpleDateFormat mDateFormat;
    private ViewGroup rootView;

    /**
     * Cria o Fragmento passando o DateFormat para mostrar o texto de forma customizada pelo usuario
     * @param sdf
     * @return
     */
    public static TwoLinesTimePicker newInstance(SimpleDateFormat sdf) {
        TwoLinesTimePicker fragment = new TwoLinesTimePicker();
        fragment.mDateFormat = sdf;

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_item_two_lines_default, container, false);
        rootView = (ViewGroup) view.findViewById(R.id.rootView);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(getContext());
            }
        });
    }

    /**
     * Mostra a Dialog do TimePicker
     * @param context
     */
    public void showDialog(Context context) {
        //Se ja temos um valor definido, mostra o Dialog com os valores atuais
        int hour;
        int minute;
        if (mValue != null) {
            hour = mValue.get(Calendar.HOUR_OF_DAY);
            minute = mValue.get(Calendar.MINUTE);
        } else {
            //senao mostra a data atual
            Calendar calendar = Calendar.getInstance();
            hour = calendar.get(Calendar.HOUR_OF_DAY);
            minute = calendar.get(Calendar.MINUTE);
        }
        boolean use24HourClock = DateFormat.is24HourFormat(context);

        TimePickerDialog timePickerDialog = new TimePickerDialog(context, listener, hour, minute, use24HourClock);
        timePickerDialog.setTitle(mTitle);
        timePickerDialog.show();
    }

    /**
     * Listener para o TimePicker
     */
    private TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int hour, int minute) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);
            setValue(calendar);
        }
    };

    /**
     * Define o Valor e se possivel atualiza no Summary
     * @param value - valor a ser setado
     */
    @Override
    public void setValue(Calendar value) {
        mValue = value;

        if (textSummary != null) {
            if (mDateFormat != null) {
                mDateFormat.setTimeZone(mValue.getTimeZone());
                textSummary.setText(mDateFormat.format(mValue.getTime()));
            } else {
                textSummary.setText(mValue.toString());
            }
        }
    }
}
