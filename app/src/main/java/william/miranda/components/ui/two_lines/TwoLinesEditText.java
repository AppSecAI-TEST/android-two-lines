package william.miranda.components.ui.two_lines;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Fragmento de duas linhas que mostra um TextEdit quando Clicado
 */
public class TwoLinesEditText extends TwoLinesDefaultFragment<String> {

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
        dialogBuilder.setView(R.layout.dialog_edit_text);

        //Acao do Botao OK
        dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                //Obtem o valor
                String value = ((EditText) ((AlertDialog)dialogInterface).findViewById(R.id.dialog_edit_text)).getText().toString();

                //Atualiza na variavel e no Summary
                setValue(value);
            }
        });

        //Acao do Botao Cancelar
        dialogBuilder.setNegativeButton("Cancelar", null);

        //Mostra o Dialog
        Dialog dialog = dialogBuilder.show();

        //Se existir um valor, preenche no campo do Dialog
        if (mValue != null) {
            EditText editText = (EditText) dialog.findViewById(R.id.dialog_edit_text);
            editText.setText(mValue);
        }
    }

    /**
     * Define o Valor e se possivel atualiza no Summary
     * @param value - valor a ser setado
     */
    @Override
    public void setValue(String value) {
        mValue = value;

        if (textSummary != null) {
            textSummary.setText(value);
        }
    }
}
