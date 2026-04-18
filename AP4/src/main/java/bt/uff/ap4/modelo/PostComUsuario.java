package bt.uff.ap4.modelo;

import lombok.experimental.Delegate;

public record PostComUsuario(
            @Delegate Post post,
            Usuario usuario
    ){

    }

