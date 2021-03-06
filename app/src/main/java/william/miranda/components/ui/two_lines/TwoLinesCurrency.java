package william.miranda.components.ui.two_lines;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

/**
 * Fragmento de duas linhas que mostra um TextEdit quando Clicado
 */
public class TwoLinesCurrency extends TwoLinesDefaultFragment<Float> {

    //Views do Layout
    private ViewGroup rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_item_two_lines_default, container, false);
        rootView = (ViewGroup) view.findViewById(R.id.rootView);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        //Define os Listeners
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(getContext());
            }
        });
    }

    /**
     * Método que mostra o Dialog
     * @param context
     */
    public void showDialog(Context context) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);

        //Define o titulo
        dialogBuilder.setTitle(mTitle);

        //Define o Layout
        dialogBuilder.setView(R.layout.dialog_edit_text_number);

        //Acao do Botao OK
        dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                //Obtem o valor
                String value = ((EditText) ((AlertDialog)dialogInterface).findViewById(R.id.dialog_edit_text)).getText().toString();

                //Atualiza na variavel e no Summary
                setValue(Float.valueOf(value));
            }
        });

        //Acao do Botao Cancelar
        dialogBuilder.setNegativeButton("Cancelar", null);

        //Mostra o Dialog
        Dialog dialog = dialogBuilder.show();
        EditText editText = (EditText) dialog.findViewById(R.id.dialog_edit_text);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        //Se existir um valor, preenche no campo do Dialog
        if (mValue != null) {

            editText.setText(String.valueOf(mValue));
        }
    }

    /**
     * Define o Valor e se possivel atualiza no Summary
     * @param value - valor a ser setado
     */
    @Override
    public void setValue(Float value) {
        mValue = value;

        if (textSummary != null) {
            textSummary.setText(formatarValor(value));
        }

        if (mChangeListener != null) {
            mChangeListener.onChange(value);
        }
    }

    /**
     * Format the Float to String currency value
     * @return
     */
    private static String formatarValor(float mValue) {
        NumberFormat format = NumberFormat.getCurrencyInstance();
        return format.format(mValue);
    }
}
