package com.jaga.core.commands.global;

import com.jaga.core.entities.BasicEntity;
import com.jaga.core.entities.render.EntityRenderer;
import com.jaga.windows.TerminalGame;

public class EntityListCommandExecutor {
    public EntityListCommandExecutor() {
        TerminalGame.appendText("Entity list: " + EntityRenderer.getEntitiesString(), true);
    }
}
