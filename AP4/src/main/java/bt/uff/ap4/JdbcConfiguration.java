package bt.uff.ap4;

import bt.uff.ap4.modelo.Post;
import lombok.val;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions;
import org.springframework.data.jdbc.core.mapping.JdbcMappingContext;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.relational.RelationalManagedTypes;
import org.springframework.data.relational.core.mapping.NamingStrategy;

import java.util.List;
import java.util.Optional;

@Configuration
@EnableJdbcRepositories(basePackageClasses = {Ap4Application.class})
public class JdbcConfiguration extends AbstractJdbcConfiguration {

    /**
     * Aqui faz um ajuste fino no JdbcData
     * @param namingStrategy
     * @param customConversions
     * @param jdbcManagedTypes
     * @return
     */
    @Override
    public JdbcMappingContext jdbcMappingContext(Optional<NamingStrategy> namingStrategy,
                                                 JdbcCustomConversions customConversions,
                                                 RelationalManagedTypes jdbcManagedTypes) {
        var mapping= super.jdbcMappingContext(namingStrategy, customConversions, jdbcManagedTypes);
        mapping.setForceQuote(false);
        return mapping;
    }

    /**
     * Aqui cria conversores de tipo
     * @return
     */
    @Override
    protected List<?> userConverters() {
        return List.of(/*Adicione os novos conversores aqui */);
    }
}