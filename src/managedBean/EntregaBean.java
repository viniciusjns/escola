package managedBean;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import util.CompactadorArquivo;
import util.ContextoUtil;
import util.MensagemUtil;
import util.UploadArquivo;
import RN.EntregaRN;
import entities.AtividadeComEntrega;
import entities.Entrega;

@ManagedBean
@SessionScoped
public class EntregaBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Entrega entrega = new Entrega();
	private Entrega entregaSelecionada = new Entrega();
	
	private AtividadeComEntrega atividade = new AtividadeComEntrega();
	private AtividadeComEntrega atividadeSelecionada = new AtividadeComEntrega();
	
	private List<Entrega> entregas;
	
	private ContextoBean contextoBean = ContextoUtil.getContextoBean();
	private UploadedFile uploadedFile = null;
	private UploadArquivo uploadArquivo = new UploadArquivo();
	private StreamedContent scfile;
	private StreamedContent scfile2;
	private StreamedContent scfile3;
	
	private MensagemUtil msg = new MensagemUtil();
	
	String caminhoZip;
	
	public String salvar() {
		
		this.entrega.setAluno(this.contextoBean.getAlunoLogado());
		this.entrega.setAtividadeComEntrega(this.atividade);
		this.entrega.setDataEntrega(new Date());
		this.entrega.setHoraEntrega(new Date());
		
		EntregaRN entregaRN = new EntregaRN();
		
		if((this.entrega.getDescricao().compareTo("") == 0) && this.uploadedFile.getSize() == 0 && this.entrega.getArquivo() == null) {
			
			this.msg.msgErro("Digite uma descrição ou selecione um arquivo!");
			
		} else {
			
			entregaRN.salvar(this.entrega);
			
			if(this.uploadedFile.getSize() != 0) {
				
				this.uploadArquivo = new UploadArquivo();
				this.uploadArquivo.fileUpload(this.entrega.getIdEntrega(), uploadedFile, "/entrega/");
		        this.uploadArquivo.gravar();
		        
		        Entrega e = entregaRN.buscarPorId(this.entrega.getIdEntrega());
		        e.setArquivo(this.uploadArquivo.getNome());
		        entregaRN.salvar(e);
			}
			
			this.msg.msgSucesso("Registro(s) inserido(s) com sucesso!");
			
		}
		
		return this.buscarEntregaPorIdAlunoIdAtividade();
		
	}
	
	public String buscarEntregaPorIdAlunoIdAtividade() {
		
		this.entrega = null;
		EntregaRN entregaRN = new EntregaRN();
		this.entrega = entregaRN.buscarEntregaPorIdAlunoIdAtividade
				(this.contextoBean.getAlunoLogado().getIdPessoa(), this.atividade.getIdAtividade());
		
		
		/*System.out.println("Aluno: " + this.contextoBean.getAlunoLogado().getNome());
		System.out.println("Atividade: " + this.atividade.getTitulo());
		System.out.println("Entrega: " + this.entrega.getIdEntrega());*/
		
		if(this.entrega == null)
			this.entrega = new Entrega();
		
		return "atividadeDetalhada";
		
	}
	
	public String verEntregas() {
		
		this.entregas = null;
		
		return "verEntregas.jsf?faces-redirect=true";
		
	}
	
	public int buscarQtdeEntregas(AtividadeComEntrega atividade) {
		
		EntregaRN entregaRN = new EntregaRN();
		
		return entregaRN.buscarEntregasPorIdAtividade(atividade.getIdAtividade()).size();
		
	}
	
	public int buscarQtdeEntregas() {
		
		EntregaRN entregaRN = new EntregaRN();
		
		return entregaRN.buscarEntregasPorIdAtividade(this.atividadeSelecionada.getIdAtividade()).size();
		
	}
	
	public StreamedContent getScFile() {
    	InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).
    			getResourceAsStream("/entrega/" + this.entrega.getIdEntrega() + "/" + this.entrega.getArquivo());
		scfile = new DefaultStreamedContent(stream, null, this.entrega.getArquivo());
    	
    	return this.scfile;
    }
	
	public StreamedContent getScFile2() {
		this.baixarEntrega();
		
    	InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).
    			getResourceAsStream("/entrega/" + this.entregaSelecionada.getIdEntrega() + "/" + this.entregaSelecionada.getAluno().getNome().replace(" ", "_") + ".zip");
		scfile2 = new DefaultStreamedContent(stream, null, this.entregaSelecionada.getAluno().getNome().replace(" ", "_") + ".zip");
    	
    	return this.scfile2;
    }
	
	public StreamedContent getScFile3() {
		this.baixarTodosArquivos();
		
    	InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).
    			getResourceAsStream("/entrega/trabalhos/" + this.atividadeSelecionada.getIdAtividade() + "/trabalhos.zip");
		scfile3 = new DefaultStreamedContent(stream, null, "trabalhos.zip");
    	
    	return this.scfile3;
    }
	
	private void baixarEntrega() {
		
		String nomeArquivo = "";
		String nomeAluno = "";
		this.caminhoZip = this.getRealPath() + "entrega\\" + this.entregaSelecionada.getIdEntrega() + "\\" + this.entregaSelecionada.getAluno().getNome().replace(" ", "_") + ".zip";
		Map<String, byte[]> arquivos = new HashMap<String, byte[]>();
		
		CompactadorArquivo compactador = new CompactadorArquivo();
		
		nomeArquivo = this.entregaSelecionada.getArquivo();
		nomeAluno = this.entregaSelecionada.getAluno().getNome();
		
		if(nomeArquivo != null)
		arquivos.put(nomeAluno + "\\" + nomeArquivo, compactador.convertFileToByteArray(this.getRealPath() + "entrega\\" + this.entregaSelecionada.getIdEntrega() + "\\" + nomeArquivo));
		
		
		try {
			
			compactador.gravar(caminhoZip, compactador.criarZip(arquivos));
		
		} catch(IOException ex) {
			
			ex.printStackTrace();
			
		}
		
	}
	
	private void baixarTodosArquivos() {
		
		EntregaRN entregaRN = new EntregaRN();
		List<Entrega> entregas = entregaRN.buscarEntregasPorIdAtividade(this.atividadeSelecionada.getIdAtividade());
		
		String nomeArquivo = "";
		String nomeAluno = "";
		this.caminhoZip = this.getRealPath() + "entrega\\trabalhos\\" + this.atividadeSelecionada.getIdAtividade() + "\\trabalhos.zip";
		Map<String, byte[]> arquivos = new HashMap<String, byte[]>();
		
		CompactadorArquivo compactador = new CompactadorArquivo();
		
		for(int i = 0; i < entregas.size(); i++) {
			
			nomeArquivo = entregas.get(i).getArquivo();
			nomeAluno = entregas.get(i).getAluno().getNome();
			
			if(nomeArquivo != null)
			arquivos.put(nomeAluno + "\\" + nomeArquivo, compactador.convertFileToByteArray(this.getRealPath() + "entrega\\" + entregas.get(i).getIdEntrega() + "\\" + nomeArquivo));
			
		}
		
		
		try {
			
			compactador.gravar(caminhoZip, compactador.criarZip(arquivos));
		
		} catch(IOException ex) {
			
			ex.printStackTrace();
			
		}
		
	}
	
	public String verificarEntrega(AtividadeComEntrega atividade) {
		
		EntregaRN entregaRN = new EntregaRN();
		Entrega e = entregaRN.buscarEntregaPorIdAlunoIdAtividade(this.contextoBean.getAlunoLogado().getIdPessoa(), atividade.getIdAtividade());
		
		if(e != null)
			return "Sim";
		
		return "Não";
		
	}

	public List<Entrega> getEntregas() {
		
		if(this.entregas == null) {
			
			EntregaRN entregaRN = new EntregaRN();
			this.entregas = entregaRN.buscarEntregasPorIdAtividade(this.atividadeSelecionada.getIdAtividade());
			
		}
		
		return this.entregas;
		
	}
	
	public String getRealPath() {
    	ServletContext sContext = (ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext();

        return sContext.getRealPath("/");
    }

	public void setEntregas(List<Entrega> entregas) {
		this.entregas = entregas;
	}
	
	public Entrega getEntrega() {
		return entrega;
	}

	public void setEntrega(Entrega entrega) {
		this.entrega = entrega;
	}

	public AtividadeComEntrega getAtividade() {
		return atividade;
	}

	public void setAtividade(AtividadeComEntrega atividade) {
		this.atividade = atividade;
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public UploadArquivo getUploadArquivo() {
		return uploadArquivo;
	}

	public void setUploadArquivo(UploadArquivo uploadArquivo) {
		this.uploadArquivo = uploadArquivo;
	}

	public AtividadeComEntrega getAtividadeSelecionada() {
		return atividadeSelecionada;
	}

	public void setAtividadeSelecionada(AtividadeComEntrega atividadeSelecionada) {
		this.atividadeSelecionada = atividadeSelecionada;
	}

	public Entrega getEntregaSelecionada() {
		return entregaSelecionada;
	}

	public void setEntregaSelecionada(Entrega entregaSelecionada) {
		this.entregaSelecionada = entregaSelecionada;
	}

}
