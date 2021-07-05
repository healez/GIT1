package com.myspace.githook_1;

public class LoggingInvocationHandler implements InvocationHandler {

	private Map<String, Method> methods = null;
	LoggingInvocationHandler(Object target) {
		orig = target;

		if (logEntryList == null) {
			logEntryList = new ArrayList<AuditDataChange>();
		}

		methods = new HashMap<String, Method>();

		for (Method method : target.getClass().getDeclaredMethods()) {
			methods.put(method.getName(), method);
		}
	}

	final Object[] noArgs = new Object[0];

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {

		String mn = method.getName();
		Object oldVal = null;
		if (mn.startsWith("set")) {
			String gn = mn.replaceFirst("s", "g");
			Method getter = methods.get(gn);
			oldVal = getter.invoke(orig, noArgs);
			methods.get(mn).invoke(orig, args);
			if (oldVal != null) {
				AuditDataChange le = new AuditDataChange();
				le.setOldData(oldVal.toString());
				le.setNewData(args[0].toString());
				le.setProperty(mn.substring(3));
				logEntryList.add(le);
			}

			return logEntryList;
		} else {
			return methods.get(mn).invoke(orig, args);
		}
	}

	public List<AuditDataChange> getDataAuditList() {
		return logEntryList;
	}

	public LoggingInvocationHandler() {

	}

}
