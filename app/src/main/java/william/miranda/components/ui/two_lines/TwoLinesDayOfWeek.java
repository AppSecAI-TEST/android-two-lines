package william.miranda.components.ui.two_lines;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import java.util.Calendar;

/**
 * Fragment que mostra os dias da semana para serem selecionados
 */
public class TwoLinesDayOfWeek extends TwoLinesDefaultFragment<SparseBooleanArray> {

    /**
     * Botoes do Layout
     */
    private ToggleButton btDomingo;
    private ToggleButton btSegunda;
    private ToggleButton btTerca;
    private ToggleButton btQuarta;
    private ToggleButton btQuinta;
    private ToggleButton btSexta;
    private ToggleButton btSabado;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_item_two_lines_day_of_week, container, false);

        //Binda as Views
        btDomingo = (ToggleButton) view.findViewById(R.id.btDomingo);
        btSegunda = (ToggleButton) view.findViewById(R.id.btSegunda);
        btTerca = (ToggleButton) view.findViewById(R.id.btTerca);
        btQuarta = (ToggleButton) view.findViewById(R.id.btQuarta);
        btQuinta = (ToggleButton) view.findViewById(R.id.btQuinta);
        btSexta = (ToggleButton) view.findViewById(R.id.btSexta);
        btSabado = (ToggleButton) view.findViewById(R.id.btSabado);

        if (mValue == null) {
            mValue = new SparseBooleanArray();
        }

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        //Define os Listeners
        btDomingo.setOnCheckedChangeListener(changeListener);
        btSegunda.setOnCheckedChangeListener(changeListener);
        btTerca.setOnCheckedChangeListener(changeListener);
        btQuarta.setOnCheckedChangeListener(changeListener);
        btQuinta.setOnCheckedChangeListener(changeListener);
        btSexta.setOnCheckedChangeListener(changeListener);
        btSabado.setOnCheckedChangeListener(changeListener);
    }

    /**
     * Listener dos Botoes
     * @param button - Botao Clicado
     * @param isChecked - Status do Botao
     */
    CompoundButton.OnCheckedChangeListener changeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton button, boolean isChecked) {
            //indice que iremos setar no array
            int index = getIndexFromButton(button.getId());

            //muda o status do dia da semana clicado
            mValue.put(index, isChecked);
        }
    };

    /**
     * Retorna se ao menos um dia da semana esta selecionado
     * @return
     */
    public boolean isSelected() {
        int size = mValue.size();

        for (int i=0 ; i<size ; i++) {
            if (mValue.valueAt(i) == true) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void setValue(SparseBooleanArray value) {
        mValue = value != null ? value : new SparseBooleanArray();

        //Para cada posicao
        for (int i=Calendar.SUNDAY ; i<=Calendar.SATURDAY ; i++) {
            //Obtem o Botao
            CompoundButton compoundButton = getButtonFromIndex(i);

            //Atualiza de acordo
            if (compoundButton != null) {
                Boolean checked = mValue.get(i);
                compoundButton.setChecked(checked != null ? checked : false);
            }
        }
    }

    /**
     * Dado o indice, retorna o botao da View
     * @param index
     * @return
     */
    private CompoundButton getButtonFromIndex(int index) {
        CompoundButton result = null;

        switch (index) {
            case Calendar.SUNDAY:
                result = btDomingo;
                break;

            case Calendar.MONDAY:
                result = btSegunda;
                break;

            case Calendar.TUESDAY:
                result = btTerca;
                break;

            case Calendar.WEDNESDAY:
                result = btQuarta;
                break;

            case Calendar.THURSDAY:
                result = btQuinta;
                break;

            case Calendar.FRIDAY:
                result = btSexta;
                break;

            case Calendar.SATURDAY:
                result = btSabado;
                break;
        }

        return result;
    }

    /**
     * Dado o Resource ID do botao, retorna o Indice
     * @param resId
     * @return
     */
    private int getIndexFromButton(int resId) {

        //Since resource IDs are not constant in Android Libraries, we'll use IF/ELSE
        if (resId == R.id.btDomingo) {
            return Calendar.SUNDAY;
        }

        if (resId == R.id.btSegunda) {
            return Calendar.MONDAY;
        }

        if (resId == R.id.btTerca) {
            return Calendar.TUESDAY;
        }

        if (resId == R.id.btQuarta) {
            return Calendar.WEDNESDAY;
        }

        if (resId == R.id.btQuinta) {
            return Calendar.THURSDAY;
        }

        if (resId == R.id.btSexta) {
            return Calendar.FRIDAY;
        }

        if (resId == R.id.btSabado) {
            return Calendar.SATURDAY;
        }

        return -1;
    }
}
