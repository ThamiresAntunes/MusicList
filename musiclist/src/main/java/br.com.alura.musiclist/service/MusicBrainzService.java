package br.com.alura.musiclist.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MusicBrainzService {

    public void buscarArtista(String nomeArtista) {
        try {
            String nomeCodificado = URLEncoder.encode(nomeArtista, "UTF-8");
            String endpoint = "https://musicbrainz.org/ws/2/artist/?query=artist:" + nomeCodificado + "&fmt=json";

            URL url = new URL(endpoint);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");
            conexao.setRequestProperty("User-Agent", "JavaMusicApp/1.0 (seuemail@example.com)");

            InputStreamReader leitor = new InputStreamReader(conexao.getInputStream());
            JsonObject json = JsonParser.parseReader(leitor).getAsJsonObject();

            JsonArray artistas = json.getAsJsonArray("artists");

            if (artistas.size() > 0) {
                JsonObject artista = artistas.get(0).getAsJsonObject();

                String nome = artista.get("name").getAsString();
                String tipo = artista.has("type") ? artista.get("type").getAsString() : "Indefinido";
                String inicio = artista.has("life-span") && artista.getAsJsonObject("life-span").has("begin")
                        ? artista.getAsJsonObject("life-span").get("begin").getAsString()
                        : "N/A";

                String id = artista.get("id").getAsString();

                var visualizarArtista = String.format("""
                        *** Artista encontrado ***
                        Nome: %s
                        Tipo: %s
                        Início de carreira: %s
                        
                        """, nome, tipo, inicio);

                System.out.println(visualizarArtista);

//                System.out.println("\nArtista encontrado:");
//                System.out.println("Nome: " + nome);
//                System.out.println("📍 País: " + pais);
//                System.out.println("👥 Tipo: " + tipo);
//                System.out.println("📅 Início de carreira: " + inicio);
//                System.out.println("🔗 ID MusicBrainz: " + id);
//                System.out.println("🔗 Link: https://musicbrainz.org/artist/" + id);
            } else {
                System.out.println("Nenhum artista encontrado com esse nome.");
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar artista: " + e.getMessage());
        }
    }
}
