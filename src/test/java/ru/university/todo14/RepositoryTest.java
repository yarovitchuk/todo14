package ru.university.todo14;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.university.todo14.model.Todo;
import ru.university.todo14.repository.TodoRepositoryInMemoryImpl;

import java.util.HashMap;
import java.util.UUID;

public class RepositoryTest {

    @Test
    public void repositoryDeleteTest() {
        // given
        var todo = new Todo(
                UUID.randomUUID(),
                "Some title"
        );
        var map = new HashMap<UUID, Todo>(){{
           put(todo.getId(), todo);
        }};
        var repo = new TodoRepositoryInMemoryImpl(map);

        // when
        repo.delete(todo.getId());

        // then
        Assertions.assertNull(map.get(todo.getId()));
    }
}
