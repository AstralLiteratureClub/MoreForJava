package bet.astral.more4j.registry;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Registry key to register objects easily in registry.
 */
public class RegistryKey {
	private final String project;
	private final String key;

	public RegistryKey(String project, String key) {
		this.project = project;
		this.key = key;
	}

	/**
	 * Returns the key of this registry key
	 * @return key
	 */
	@NotNull
	public String getKey() {
		return key;
	}

	/**
	 * Returns the project name space of this key
	 * @return project namespace
	 */
	@NotNull
	public String getProject() {
		return project;
	}

	/**
	 * Returns the project name and key separated using ":"
	 * @return combined key
	 */
	public String toString(){
		return project+":"+key;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) return true;
		if (!(object instanceof RegistryKey that)) return false;
		return Objects.equals(project, that.project) && Objects.equals(key, that.key);
	}

	@Override
	public int hashCode() {
		return Objects.hash(project, key);
	}
}
