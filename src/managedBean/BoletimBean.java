package managedBean;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import util.AbreBrowser;
import util.PeriodoLetivoDataModel;
import RN.AlunoTurmaRN;
import RN.FaltaRN;
import RN.NotaRN;
import RN.PeriodoLetivoRN;
import RN.ProfessorTurmaRN;
import RN.TurmaRN;
import entities.AlunoTurma;
import entities.Falta;
import entities.Nota;
import entities.PeriodoLetivo;
import entities.ProfessorDisciplina;
import entities.Turma;

@ManagedBean
@ViewScoped
public class BoletimBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idAnoSelecionado;
	private int idTurmaSelecionada;
	private String observacao;
	
	private List<Turma> turmaList;
	private List<PeriodoLetivo> periodoList = new ArrayList<PeriodoLetivo>();
	private List<PeriodoLetivo> periodos;
	private List<AlunoTurma> alunoTurmaList = new ArrayList<AlunoTurma>();
	private List<Nota> notaList;
	private List<Falta> faltaList;
	
	private AlunoTurma alunoTurma = new AlunoTurma();
	private PeriodoLetivoDataModel periodoLetivoDataModel; 
	
	
	public void buscarAlunos() {
		
		this.buscarPeriodos();
		
		AlunoTurmaRN atRN = new AlunoTurmaRN();
		this.alunoTurmaList = atRN.buscarAlunoTurmaPorIdTurmaAtivo(this.idTurmaSelecionada);
		
	}
		
	public void gerarBoletim() {
		
		try {
			float totalNotasPeriodo = 0;
			float totalNotasFinal = 0;
			int totalFaltasPeriodo = 0;
			int totalFaltasFinal = 0;
			int width = 250 + (85 * (this.periodoList.size() + 1));
			
			String nomeAluno = this.alunoTurma.getAluno().getNome().toUpperCase();
			String ano = this.alunoTurma.getTurma().getAno().getDescricao();
			String nivelEnsino = this.alunoTurma.getTurma().getSerie().getDescricao().substring(0, 6).toUpperCase();
			String turno = this.alunoTurma.getTurma().getTurno().getDescricao().toUpperCase();
			String turma = this.alunoTurma.getTurma().getDescricao().toUpperCase();
			String anoEnsino = this.alunoTurma.getTurma().getSerie().getDescricao().substring(7, 19).toUpperCase();
			int numeroAluno = this.alunoTurmaList.indexOf(alunoTurma) + 1;
			
			String diretorio = this.getRealPath();
			String caminho = diretorio.replace("\\", "/");
			
			File file = new File(caminho + "boletim");
            
            if(!file.exists())
            	file.mkdirs();
			
			FileWriter fileR = new FileWriter(caminho + "boletim" + "/boletim.html");
		    BufferedWriter buff = new BufferedWriter(fileR);
		    
		    buff.write(
		    
		    "<html xmlns=\"http://www.w3.org/1999/xhtml\"><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\" /><title>Boletim</title>"
		    + "<style type=\"text/css\">.rotacao {float: left;display: block;margin-top: 50px;-webkit-transform: rotate(-90deg);-o-transform: rotate(-90deg);-moz-transform: rotate(-90deg);filter: progid:DXImageTransform.Microsoft.BasicImage(rotation=3);}</style>"
		    + "</head><body style=\"font-family: 'arial';\"><div id=\"container\" style=\"margin: 0 auto; display: table; width: 800px; height: auto; border: solid 0px #f00\"><div id=\"cabecalho\" style=\"float: left; display: block; width: 798px; height: 100px; border: solid 1px #000;\"><div id=\"logo1\" style=\"float: left; display: block; width: 89px; height: 78px; margin: 5px 0 0 5px;\"><img src=\"../resources/imagens/logo1.jpg\" width=\"89px\" height=\"78px\"/></div><h3 style=\"float: left; display: block; width: 600px; margin: 5px 0 0 0; font-size: 16px; text-align: center;\">Boletim Escolar</h3><h3 style=\"float: left; display: block; width: 600px; margin: 5px 0 0 0; font-size: 14px; text-align: center;\">EE MARCOLINO DE BARROS</h3><h3 style=\"float: left; display: block; width: 600px; margin: 5px 0 0 0; font-size: 12px; text-align: center;\">AV GETÚLIO VARGAS, 367, CENTRO, PATOS DE MINAS, Telefone: (34)38212652</h3><h3 style=\"float: left; display: block; width: 600px; margin: 15px 0 0 0; font-size: 14px; text-align: center;\">SRE PATOS DE MINAS</h3><div id=\"logo2\" style=\"float: right; display: block; width: 85px; height: 83px; margin: -55px 10px 0 0;\"><img src=\"../resources/imagens/logo2.jpg\" width=\"85px\" height=\"83px\"/></div></div><div id=\"dadosPessoais\" style=\"float: left; display: block; border: solid 1px #000; width:798px; margin: 1px 0 0 0;\">"
		    + "<table style=\"float: left; display: block; width:798px; font-size: 14px;\"><tr><td colspan=\"3\" style=\"border: solid 1px #000;\">Nome: " + nomeAluno + "</td><td style=\"border: solid 1px #000;\">Ano: " + ano + "</td></tr><tr><td colspan=\"4\" style=\"border: solid 1px #000;\">Nível de Ensino: " + nivelEnsino + "</td></tr><tr><td style=\"border: solid 1px #000;\">Turno: " + turno + "</td><td style=\"border: solid 1px #000;\">Turma: " + turma + "</td><td style=\"border: solid 1px #000;\">Ano: " + anoEnsino + "</td><td style=\"border: solid 1px #000;\">Número: " + numeroAluno + "</td></tr></table></div>"
		    + "<div id=\"notas\" style=\"float: left; display: block; width: " + width + "px; height: auto; margin: 1px 0 0 0; border: 0px solid #0ff;\"><div id=\"divisaoDisciplina\" style=\"float: left; display: block; width: 200px; height: 150px; border: 1px solid #000\"><div id=\"divi\" style=\"float: left; display: block; width: 198px; height: 74px; border: #f00 solid 0px;\"><h4 style=\"float: right; display: block; margin: 5px 10px 0 0;\">Divisões</h4></div><div id=\"disc\" style=\"float: left; display: block; width: 198px; height: 74px; border: #f00 solid 0px;\"><h4 style=\"float: left; display: block; margin: 50px 0 0 10px;\">Disciplinas</h4></div></div>"
		    
		    );

			
			ProfessorTurmaRN ptRN = new ProfessorTurmaRN();
			FaltaRN faltaRN = new FaltaRN();
			NotaRN notaRN = new NotaRN();
			
			List<ProfessorDisciplina> professorDisciplinaList = ptRN.buscarProfessorDisciplinaAtivoIdTurma(this.idTurmaSelecionada); //busca as disciplinas que tem a turma q o aluno esta cadastrado
			
			for(int aux = 0; aux < periodoList.size(); aux++) {
				
				//System.out.println(periodoList.get(aux).getDescricao());
				
				buff.write(
						
				"<div id=\"periodo1\" style=\"float: left; display: table; width: 84px; height: 150px; margin: 0 0 0 1px; border: 0px solid #000; \"><div id=\"periodo2\" style=\"float: left; display: block; width: 85px; height: 127px; border: 1px solid #000; \"><p class=\"rotacao\">" + periodoList.get(aux).getDescricao() + "</p></div><div id=\"apr\" style=\"float: left; display: block; width: 41px; height: 20px; margin: 1px 0 0 0; border: 1px solid #000; \">Apr</div><div id=\"fal\" style=\"float: left; display: block; width: 41px; height: 20px; margin: 1px 0 0 1px; border: 1px solid #000; \">Fal</div></div>"
				
				);
			}
			
			buff.write(
					
					"<div id=\"periodo1\" style=\"float: left; display: table; width: 84px; height: 150px; margin: 0 0 0 1px; border: 0px solid #000; \"><div id=\"periodo2\" style=\"float: left; display: block; width: 85px; height: 127px; border: 1px solid #000; \"><p class=\"rotacao\" style=\"margin-left: 20px\">Total</p></div><div id=\"apr\" style=\"float: left; display: block; width: 41px; height: 20px; margin: 1px 0 0 0; border: 1px solid #000; \">Apr</div><div id=\"fal\" style=\"float: left; display: block; width: 41px; height: 20px; margin: 1px 0 0 1px; border: 1px solid #000; \">Fal</div></div>"
			
			);
			
			
			//for para preencher objeto com as notas em suas determinadas disciplinas
			for(int i = 0; i < professorDisciplinaList.size(); i++) {
				
				for(int aux = 0; aux < periodoList.size(); aux++) {
					
					//busca todas as notas do aluno
					notaList = notaRN.buscarPorIdAlunoIdPeriodo(this.alunoTurma.getAluno().getIdPessoa(), periodoList.get(aux).getIdPeriodoLetivo());
					//busca todas as faltas do aluno
					faltaList = faltaRN.buscarFaltasPorPeriodoAlunoTurma(periodoList.get(aux).getIdPeriodoLetivo(), this.alunoTurma.getAluno().getIdPessoa(), this.idTurmaSelecionada);
				
					//System.out.println("\tDisciplina: " + professorDisciplinaList.get(i).getDisciplina().getDescricao());
					if(aux == 0) {
						
						buff.write(
						
							"<div id=\"disciplina\" style=\"float: left; display: block; width: 200px; height: 18px; margin: 1px 1px 0 0; border: 1px solid #000;\">" + professorDisciplinaList.get(i).getDisciplina().getDescricao().toUpperCase() + "</div>"	
								
						);
					}
					
					for(int j = 0; j < notaList.size(); j++) {
						
						if(professorDisciplinaList.get(i).getDisciplina().getIdDisciplina() == notaList.get(j).getAtividade().getProfessorTurma().getProfessorDisciplina().getDisciplina().getIdDisciplina()) {
							
							totalNotasPeriodo += notaList.get(j).getValor();
							totalNotasFinal += notaList.get(j).getValor();//testar isso
							
						}
						
					}
					
					for(int c = 0; c < faltaList.size(); c++) {
						
						if(faltaList.get(c).getAula().getProfessorTurma().getProfessorDisciplina().getDisciplina().getIdDisciplina() == professorDisciplinaList.get(i).getDisciplina().getIdDisciplina()) {
							
							totalFaltasPeriodo++;
							totalFaltasFinal++;//testar isso
							
						}
						
					}
					
					buff.write(
							
							"<div id=\"notaFalta\" style=\"float: left; display: block; width: 88px; height: 20px; margin: 1px 0 0 0px; border: 0px solid #000; text-align:center;\"><div id=\"nota\" style=\"float: left; display: block; width: 41px; height: 18px; margin: 0 1px 0 0; border: 1px solid #000;\">" + totalNotasPeriodo + "</div><div id=\"falta\" style=\"float: left; display: block; width: 41px; height: 18px; border: 1px solid #000;\">" + totalFaltasPeriodo + "</div></div>"
					
					);
					
					totalNotasPeriodo = 0;
					totalFaltasPeriodo = 0;
					
				}
			
				
				buff.write(
						
						"<div id=\"notaFalta\" style=\"float: left; display: block; width: 88px; height: 20px; margin: 1px 0 0 0px; border: 0px solid #000; text-align:center;\"><div id=\"nota\" style=\"float: left; display: block; width: 41px; height: 18px; margin: 0 1px 0 0; border: 1px solid #000;\">" + totalNotasFinal + "</div><div id=\"falta\" style=\"float: left; display: block; width: 41px; height: 18px; border: 1px solid #000;\">" + totalFaltasFinal + "</div></div>"
				
				);
				
				totalNotasFinal = 0;
				totalFaltasFinal = 0;
			}
			
			notaList = new ArrayList<Nota>();
			faltaList = new ArrayList<Falta>();
			
			buff.write(
					
				"<div id=\"rodape\" style=\"float: left; display: block; width: 798px; height: 78px; margin: 20px 0 0 -2px; border: solid 0px #000; \"><fieldset style=\"float: left; display: block; width: 775px; height: 60px; border: solid 1px #000;\"><legend>Obervações</legend><span style=\"font-size: 12px;\">" + this.getObservacao() + "</span></fieldset></div>"
				+ "</div></body></html>"
						
			);
			buff.close();
			
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		this.abreBrowser();
	}
	
	private void abreBrowser() {
		
		try {
            AbreBrowser browser = new AbreBrowser();
            String diretorio = this.getRealPath();
			String caminho = diretorio.replace("\\", "/");
            
            String url = ("file:///" + caminho + "boletim/boletim.html");
            String urlSend = browser.URLCaracteresInvalidos(url);
            browser.AbreBrowser(urlSend);
		} catch(Exception e) {
            e.printStackTrace();
        }
		
	}
	
	private String getRealPath() {
    	ServletContext sContext = (ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext();

        return sContext.getRealPath("/");
    }
	
	public void trocarTurma() {
		
		if((Integer) this.idAnoSelecionado != null || this.idAnoSelecionado != 0) {
			
			TurmaRN turmaRN = new TurmaRN();
			this.turmaList = turmaRN.buscarPorIdAno(this.idAnoSelecionado);
			
		} else {
			
			this.turmaList = new ArrayList<Turma>();
			
		}
		
	}
	
	public void buscarPeriodos() {
		
		/*if((Integer) this.idAnoSelecionado != null || this.idAnoSelecionado != 0) {
			
			PeriodoLetivoRN periodoRN = new PeriodoLetivoRN();
			this.periodos = periodoRN.buscarPorIdAno(this.idAnoSelecionado);
			
			this.periodoLetivoDataModel = new PeriodoLetivoDataModel(this.periodos);
			
		} else {
			
			this.periodos = new ArrayList<PeriodoLetivo>();
			this.periodoLetivoDataModel = new PeriodoLetivoDataModel(this.periodos);
			
		}*/
		PeriodoLetivoRN periodoRN = new PeriodoLetivoRN();
		this.periodoList = periodoRN.buscarPorIdAno(this.idAnoSelecionado);
		
	}


	public int getIdAnoSelecionado() {
		return idAnoSelecionado;
	}


	public void setIdAnoSelecionado(int idAnoSelecionado) {
		this.idAnoSelecionado = idAnoSelecionado;
	}


	public int getIdTurmaSelecionada() {
		return idTurmaSelecionada;
	}


	public void setIdTurmaSelecionada(int idTurmaSelecionada) {
		this.idTurmaSelecionada = idTurmaSelecionada;
	}


	public List<Turma> getTurmaList() {
		return turmaList;
	}


	public void setTurmaList(List<Turma> turmaList) {
		this.turmaList = turmaList;
	}


	public List<PeriodoLetivo> getPeriodoList() {
		return periodoList;
	}


	public void setPeriodoList(List<PeriodoLetivo> periodoList) {
		this.periodoList = periodoList;
	}


	public List<PeriodoLetivo> getPeriodos() {
		return periodos;
	}


	public void setPeriodos(List<PeriodoLetivo> periodos) {
		this.periodos = periodos;
	}

	public PeriodoLetivoDataModel getPeriodoLetivoDataModel() {
		return periodoLetivoDataModel;
	}

	public void setPeriodoLetivoDataModel(
			PeriodoLetivoDataModel periodoLetivoDataModel) {
		this.periodoLetivoDataModel = periodoLetivoDataModel;
	}

	public List<AlunoTurma> getAlunoTurmaList() {
		
		return this.alunoTurmaList;
		
	}

	public void setAlunoTurmaList(List<AlunoTurma> alunoTurmaList) {
		this.alunoTurmaList = alunoTurmaList;
	}

	public AlunoTurma getAlunoTurma() {
		return alunoTurma;
	}

	public void setAlunoTurma(AlunoTurma alunoTurma) {
		this.alunoTurma = alunoTurma;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}
