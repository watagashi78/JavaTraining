package ex17_04;

import java.lang.ref.WeakReference;

public class ResourceImpl implements Resource {
	WeakReference<Object> keyRef;
	boolean needsRelease = false;

	ResourceImpl(Object key) {
		keyRef = new WeakReference<Object>(key);

		// .. 外部リソースの設定

		needsRelease = true;
	}

	@Override
	public void use(Object key, Object... args) {
		if (!keyRef.get().equals(key)) {
			throw new IllegalArgumentException("wrong key");
		}

		// ... リソースの利用 ...

	}

	@Override
	public void release() {
		if (needsRelease) {
			needsRelease = false;

			// .. リソースの解放 ...

		}
	}
}
