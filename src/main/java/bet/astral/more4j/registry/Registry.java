package bet.astral.more4j.registry;

import bet.astral.more4j.registry.exception.RegistryMutationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Registry allows registration of elements.
 * @param <T> Registry type
 */
public interface Registry<T> extends Iterable<T> {
	/**
	 * Returns if the given registry key has any value registered to it
	 * @param registryKey registry key
	 * @return true if found, else false
	 */
	boolean isRegistered(@NotNull RegistryKey registryKey);

	/**
	 * Returns true if registry allows new registrations
	 * @return true if allows new registrations, else false
	 */
	boolean acceptsRegisterations();

	@NotNull
	static <T> T register(@NotNull Registry<T> registry, @NotNull RegistryKey key, @NotNull T value) throws RegistryMutationException {
		if (!registry.acceptsRegisterations()){
			throw new RegistryMutationException(value, key);
		}
		if (registry instanceof MutableRegistry<T> mutableRegistry){
			return mutableRegistry.register(key, value);
		}
		return value;
	}

	/**
	 * Returns null if no registered value is found for given registry key
	 * @param registryKey registry key
	 * @return value, else null
	 */
	@Nullable
	T getValue(@NotNull RegistryKey registryKey);

	/**
	 * Returns null if no registered value is found for given registry key from registrable
	 * @param registrable registered
	 * @return value, else null
	 */
	@Nullable
	T getValue(@NotNull Registrable<?> registrable);
}