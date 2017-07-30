package william.miranda.components.ui.two_lines;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Fragmento de duas linhas que mostra um RadioGroup quando Clicado
 * O Retorno sera o indice do Radio selecionado e deve ser tratado fora deste componente
 */
public class TwoLinesRadioGroup extends TwoLinesDefaultFragment<Integer> {

    /**
     * Views
     */
    private ViewGroup rootView;

    /**
     * Argumento dos Textos
     */
    private String[] mOptionsText;

    /**
     * Argumento dos Valores
     */
    private int[] mOptionsValue;

    /**
     * Mapa de opcoes com os valores associados
     */
    Map<Integer, String> mMapOptionValue;

    /**
     * Argumento do indice a ser selecionado por default
     */
    int mSelectedIndex = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Infla a View
        View view = inflater.inflate(R.layout.list_item_two_lines_default, container, false);

        //Binda as Views
        rootView = (ViewGroup) view.findViewById(R.id.rootView);

        //Obtem os Argumentos
        mOptionsText = getArguments().getStringArray("options_text");
        mOptionsValue = getArguments().getIntArray("options_value");
        mSelectedIndex = getArguments().getInt("selected_index", 0);

        //Preenche o Mapa
        mMapOptionValue = new LinkedHashMap<>();

        for (int i=0 ; i<mOptionsText.length ; i++) {
            mMapOptionValue.put(mOptionsValue[i], mOptionsText[i]);
        }

        //Retorna a View
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
     * Mostra a Dialog contendo as opções
     * @param context
     */
    public void showDialog(Context context) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setTitle(mTitle);
        dialogBuilder.setSingleChoiceItems(mMapOptionValue.values().toArray(new String[0]), mSelectedIndex, clickListener);

        dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                /* Precisamos obter no mapa o valor do indice selecionado
                    Entao iteramos o mapa e se acharmos, obtemos o valor do item selecionado
                 */
                int i=0;
                for (Map.Entry<Integer, String> entry : mMapOptionValue.entrySet()) {
                    if (i == mSelectedIndex) {
                        setValue(entry.getKey());
                    }
                    i++;
                }
            }
        });

        dialogBuilder.setNegativeButton("Cancelar", null);
        dialogBuilder.show();
    }

    @Override
    public void setValue(Integer value) {
        //grava o valor
        mValue = value;

        //obtem a string e popula o summary
        if (textSummary != null) {
            textSummary.setText(mMapOptionValue.get(mValue));
        }
    }

    private DialogInterface.OnClickListener clickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int which) {
            //Atualiza o indice
            mSelectedIndex = which;
        }
    };
}
