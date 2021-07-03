package com.myspace.githook_1;

public class LoggingInvocationHandler implements InvocationHandler {

	private Map<String, Method> methods = null;

	LoggingInvocationHandler(Object target,
	// Object ctx,
			String _logEntryListGlobalName) {

		orig = target;
		logEntryListGlobalName = _logEntryListGlobalName;

		methods = null;
		runtime = null;
		logEntryList = null;

		if (methods == null) {

			methods = new HashMap<String, Method>();

			for (Method method : target.getClass().getDeclaredMethods()) {
				System.out.println(method.getName());
				methods.put(method.getName(), method);
			}
		}
	}

	final Object[] noArgs = new Object[0];

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {

		System.out.println("---->kContext:" + ruleContext);

		logEntryList = (List<String>) ruleContext.getKieRuntime().getGlobal(
				logEntryListGlobalName);

		// if ( ruleContext == null ) {
		// throw new IllegalStateException( "No RuleContext set" );
		// }

		String mn = method.getName();

		// System.out.println("mn:" + mn );

		Object oldVal = null;

		if (mn.startsWith("set")) {

			String gn = mn.replaceFirst("s", "g");

			System.out.println("gn:" + gn);

			Method getter = methods.get(gn);

			System.out.println("gttr:" + getter);

			oldVal = getter.invoke(orig, noArgs);

			methods.get(mn).invoke(orig, args);

			if (oldVal != null) {

				System.out.println("Old" + oldVal);
				System.out.println("New" + args[0]);

				StringBuffer le = new StringBuffer();

				le.append(mn.substring(3));
				le.append("FieldChange");
				le.append(oldVal.toString());
				le.append(args[0].toString());
				le.append(orig.getClass().getName());

				Object idGetter = methods.get("getId");

				// le.setId(( idGetter != null )? getter.invoke(orig, noArgs
				// ).toString() : "NO-ID");

				if (runtime != null) {
					setRuleData(le);
				}

				logEntryList.add(le.toString());
			}

			return null;
		} else {
			Object result = methods.get(mn).invoke(orig, args);
			return result;
		}
	}

	protected void setRuleData(StringBuffer le) {
		System.out.println("RuleName=ID:" + ruleContext.getRule().getName());
		// ruleContext.getMatch().getRule().
	}

	private LoggingInvocationHandler(KieRuntime kieruntime) {
		super();
		System.out.println("KieRunTime:" + kieruntime);
		this.runtime = kieruntime;
		logEntryList = (List<String>) kieruntime
				.getGlobal(logEntryListGlobalName);

	}

	public LoggingInvocationHandler() {
	}
}
