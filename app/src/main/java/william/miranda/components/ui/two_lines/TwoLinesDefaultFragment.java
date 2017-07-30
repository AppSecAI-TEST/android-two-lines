package william.miranda.components.ui.two_lines;

import android.support.v4.app.Fragment;
import android.widget.TextView;

/**
 * Fragment que mostra duas linhas
 */
public abstract class TwoLinesDefaultFragment<T> extends Fragment {

    public interface TwoLinesChangeListener<T> {
        void onChange(T newValue);
    }

    // Elementos de Layout
    protected TextView textTitle;
    protected TextView textSummary;

    /**
     * Valor do componente
     */
    protected T mValue;

    // Valores que podem ser passados
    protected String mTitle;
    protected String mSummary;

    //Change Listener
    protected TwoLinesChangeListener<T> mChangeListener;

    @Override
    public void onStart() {
        super.onStart();

        //Binda as Views
        textTitle = (TextView) getView().findViewById(R.id.title);
        textSummary = (TextView) getView().findViewById(R.id.summary);

        //AfterViews
        //Populamos titulo e summary
        textTitle.setText(mTitle);
        textSummary.setText(mSummary);

        //Se ja setamos um valor, mostramos
        if (mValue != null) {
            setValue(mValue);
        }
    }

    /**
     * Retorna o valor do Componente
     * @return
     */
    public T getValue() {
        return mValue;
    }

    /**
     * Seta o valor do Componete
     * Dependendo do componente, devemos atualizar o Summary,
     * ou preencher um CheckBox
     * @param value - valor a ser setado
     */
    public abstract void setValue(T value);

    /**
     * Define o Titulo
     * @param title
     */
    public void setTitle(String title) {
        mTitle = title;
    }

    /**
     * Define o Sumario
     * @param summary
     */
    public void setSummary(String summary) {
        mSummary = summary;
    }

    /**
     * Define o ChangeListener
     * @param changeListener
     */
    public void setChangeListener(TwoLinesChangeListener<T> changeListener) {
        mChangeListener = changeListener;
    }
}
