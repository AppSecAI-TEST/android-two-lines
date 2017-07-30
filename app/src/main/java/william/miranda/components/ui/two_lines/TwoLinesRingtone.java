package william.miranda.components.ui.two_lines;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static android.app.Activity.RESULT_OK;

/**
 * Fragmento de duas linhas que mostra o Ringtone Picker quando Clicado
 */
public class TwoLinesRingtone extends TwoLinesDefaultFragment<Uri> {

    private static final int RINGTONE_REQUEST_CODE = 4;

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

        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, mSummary);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_SILENT, true);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_DEFAULT, false);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_ALARM);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, mValue);
                startActivityForResult(intent, RINGTONE_REQUEST_CODE);
            }
        });

        //Ao criar, definimos o valor
        setValue(mValue);
    }

    /**
     * Quando o usuario escolhe um Ringtone, salva o Valor
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RINGTONE_REQUEST_CODE && resultCode == RESULT_OK) {
            Uri uri = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
            setValue(uri);
        }
    }

    @Override
    public void setValue(Uri value) {
        mValue = value;

        if (textSummary != null) {

            //se ha valor, tentamos mostrar o nome do Ringtone
            if (mValue != null) {
                Ringtone ringtone = RingtoneManager.getRingtone(getContext(), mValue);
                if (ringtone != null) {
                    String ringtoneTitle = ringtone.getTitle(getContext());
                    textSummary.setText(ringtoneTitle);
                }
            } else {
                //senao, mostramos um texto default
                //textSummary.setText(R.string.ringtone_none);
            }

            if (mChangeListener != null) {
                mChangeListener.onChange(mValue);
            }
        }
    }
}
