package bt.uff.ap4.controller;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("aviso_arquivo")
@AllArgsConstructor
public class AvisoArquivoController {

    @GetMapping("unitario")
    public ModelAndView getUnitario(){

        var mv = new ModelAndView("aviso_arquivo/unitario/get");

        mv.addObject("nome_da_pessoa", "Marcos");
        mv.addObject("nome_do_documento", "apresentação.pptx");

        return mv;
    }

    record Documento(
            String nome,
            long tamanho
    ){}

    @GetMapping("varios")
    public ModelAndView getVarios(){

        var mv = new ModelAndView("aviso_arquivo/varios/get");

        mv.addObject("nome_da_pessoa", "André");
        mv.addObject("documentos", criaDocumentos());

        return mv;
    }

    private List<Documento> criaDocumentos() {
        return List.of(
                new Documento("curso_computação.xlsx", 100),
                new Documento("curso_química.xlsx", 150),
                new Documento("curso_psicologia.xlsx", 90),
                new Documento("curso_medicina.xlsx", 200),
                new Documento("curso_direito.xlsx", 120),
                new Documento("curso_engenharia_civil.xlsx", 180),
                new Documento("curso_biologia.xlsx", 110),
                new Documento("curso_historia.xlsx", 85),
                new Documento("curso_geografia.xlsx", 95),
                new Documento("curso_matematica.xlsx", 130),
                new Documento("curso_fisica.xlsx", 140),
                new Documento("curso_letras.xlsx", 75),
                new Documento("curso_pedagogia.xlsx", 80),
                new Documento("curso_arquitetura.xlsx", 160),
                new Documento("curso_design.xlsx", 115),
                new Documento("curso_economia.xlsx", 125),
                new Documento("curso_administracao.xlsx", 105),
                new Documento("curso_enfermagem.xlsx", 135),
                new Documento("curso_fisioterapia.xlsx", 128),
                new Documento("curso_odontologia.xlsx", 190),
                new Documento("curso_veterinaria.xlsx", 170),
                new Documento("curso_jornalismo.xlsx", 92),
                new Documento("curso_publicidade.xlsx", 98),
                new Documento("curso_sociologia.xlsx", 88),
                new Documento("curso_filosofia.xlsx", 82),
                new Documento("curso_artes_visuais.xlsx", 112),
                new Documento("curso_musica.xlsx", 108),
                new Documento("curso_gastronomia.xlsx", 145),
                new Documento("curso_turismo.xlsx", 97),
                new Documento("curso_moda.xlsx", 103)
        );
    }
}
