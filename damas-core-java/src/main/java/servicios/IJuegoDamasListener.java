package servicios;

import java.util.List;

import dominio.CasilleroNegro;
import dominio.Ficha;
import excepciones.CasilleroOcupadoException;
import excepciones.FormatoCasilleroException;
import excepciones.NoExisteCasilleroDisponibleException;
import excepciones.NoHayFichaEnCasilleroException;
import excepciones.NoPuedoComerFichaException;
import excepciones.NoTieneFichaContrarioException;

public interface IJuegoDamasListener {

	public abstract void init();

	public abstract boolean moveFicha(String fichaStr, String casilleroStr)
			throws NoHayFichaEnCasilleroException, FormatoCasilleroException,
			CasilleroOcupadoException, NoExisteCasilleroDisponibleException,
			NoTieneFichaContrarioException, NoPuedoComerFichaException;

	public abstract String botMueveACasillero();

	public abstract List<CasilleroNegro> dameCasillerosDisponibles(
			String fichaStr) throws NoHayFichaEnCasilleroException,
			FormatoCasilleroException;

	public abstract List<Ficha> dameFichasBlancas();

	public abstract List<CasilleroNegro> dameCasillerosNegros();

	public abstract List<Ficha> dameFichasNegras();

}