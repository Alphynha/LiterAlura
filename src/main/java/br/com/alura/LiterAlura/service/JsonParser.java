package br.com.alura.LiterAlura.service;

import br.com.alura.LiterAlura.model.LivroRecord;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JsonParser {

    private ObjectMapper objectMapper = new ObjectMapper();

    public LivroRecord converterLivro(String json) {

        try {
            return objectMapper.readValue(json, LivroRecord.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public List<LivroRecord> converterLivros(String json) {
        List<LivroRecord> lista = new ArrayList<>();

        try {

            JsonNode jsonObject = objectMapper.readTree(json);
            JsonNode resultados = jsonObject.get("results");

            for (JsonNode node : (ArrayNode) resultados) {
                LivroRecord livro = objectMapper.treeToValue(node, LivroRecord.class);
                lista.add(livro);
            }

            return lista;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
