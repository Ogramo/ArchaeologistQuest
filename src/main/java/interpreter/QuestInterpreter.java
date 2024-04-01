package interpreter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import lombok.SneakyThrows;
import objects.Quest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Getter
public class QuestInterpreter {
    private final Quest quest;

    public QuestInterpreter(String pathToJson) {
        quest = loadJsonGame(pathToJson);
    }

    @SneakyThrows
    private Quest loadJsonGame(String pathToJson) {
        final ClassLoader loader = getClass().getClassLoader();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        Objects.requireNonNull(loader.getResourceAsStream(pathToJson)), StandardCharsets.UTF_8))) {
            Gson gson = new Gson();
            Type type = new TypeToken<Quest>() {
            }.getType();
            return gson.fromJson(reader, type);
        }
    }
}
