package bet.astral.more4j.registry;

import org.jetbrains.annotations.NotNull;

/**
 * Makes objects contains
 * @param <T>
 */
public interface Registrable<T extends Registrable<?>> {
	/**
	 * Returns the registry key associated with this registrable
	 * @return registry key
	 */
	@NotNull
	RegistryKey getRegistryKey();
	/**
	 * Returns true if this is registered in the given registry.
	 * @param registry registry
	 * @return true if registered, else false
	 */
	default boolean isRegistered(@NotNull Registry<T> registry) {
		return registry.isRegistered(getRegistryKey());
	}
}
