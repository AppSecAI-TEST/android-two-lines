package william.miranda.components.ui.two_lines;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Fragmento de duas linhas que mostra um DatePicker quando Clicado
 */
public class TwoLinesDatePicker extends TwoLinesDefaultFragment<Calendar> {

    private SimpleDateFormat mDateFormat;
    private ViewGroup rootView;

    /**
     * Cria o Fragmento passando o DateFormat para mostrar o texto de forma customizada pelo usuario
     * @param sdf
     * @return
     */
    public static TwoLinesDatePicker newInstance(SimpleDateFormat sdf) {
        TwoLinesDatePicker fragment = new TwoLinesDatePicker();
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

        //Ao clicar mostra o DatePickerDialog
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(getContext());
            }
        });
    }

    /**
     * Mostra a Dialog do DatePicker
     * @param context
     */
    public void showDialog(Context context) {
        //Se ja temos um valor definido, mostra o Dialog com os valores atuais
        int year;
        int month;
        int day;
        if (mValue != null) {
            year = mValue.get(Calendar.YEAR);
            month = mValue.get(Calendar.MONTH);
            day = mValue.get(Calendar.DAY_OF_MONTH);
        } else {
            //senao mostra a data atual
            Calendar calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);
        }

        DatePickerDialog datePickerDialog = new DatePickerDialog(context, listener, year, month, day);
        datePickerDialog.setTitle(mTitle);
        datePickerDialog.show();
    }

    /**
     * Listener para o TimePicker
     */
    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);
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
