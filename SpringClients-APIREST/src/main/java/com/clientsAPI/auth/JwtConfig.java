package com.clientsAPI.auth;

public class JwtConfig {
	
	public static final String SECRET_KEY = "Magic_Secret";
	
	public static final String RSA_PUBLIC = "-----BEGIN PUBLIC KEY-----\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvuZ7Qe3OjPE4WDuPUFnb\n" + 
			"LFZO+1U5vqVOAUCaAHIOdNzlrCl3U67PRqsH3ZiTDQ7BO74jjVZviGpwi9tc6fsj                                                                     \n" + 
			"EicsQS5XhEI/dRSPbgzD5OFbUaz1zwuDKboXdn9jtRQGg7p1iONq8spAri4/aFER                                                                     \n" + 
			"3FedFnzaG2cEJ8WmuTT/B08LNMRLAbA5k8yinI4ORS0OZkDx/j59KqcuOEjWxmrd                                                                     \n" + 
			"j+gUglzEz6jcPlDjKCcq0ggWMQPi7BM2PumIDYlYLbVOl234hm/UgQFNzcX6muUv                                                                     \n" + 
			"c/pVxDMk4U+o8LZ8wEXazfAx2aeg4mqBjcYL3b3ywtHH0gAI5ZdXxY7Oj2fOF/Q+                                                                     \n" + 
			"QwIDAQAB                                                                                                                             \n" + 
			"-----END PUBLIC KEY-----";
			
	public static final String RSA_PRIVATE = "-----BEGIN RSA PRIVATE KEY-----\n" + 
			"MIIEowIBAAKCAQEAvuZ7Qe3OjPE4WDuPUFnbLFZO+1U5vqVOAUCaAHIOdNzlrCl3\n" + 
			"U67PRqsH3ZiTDQ7BO74jjVZviGpwi9tc6fsjEicsQS5XhEI/dRSPbgzD5OFbUaz1\n" + 
			"zwuDKboXdn9jtRQGg7p1iONq8spAri4/aFER3FedFnzaG2cEJ8WmuTT/B08LNMRL\n" + 
			"AbA5k8yinI4ORS0OZkDx/j59KqcuOEjWxmrdj+gUglzEz6jcPlDjKCcq0ggWMQPi\n" + 
			"7BM2PumIDYlYLbVOl234hm/UgQFNzcX6muUvc/pVxDMk4U+o8LZ8wEXazfAx2aeg\n" + 
			"4mqBjcYL3b3ywtHH0gAI5ZdXxY7Oj2fOF/Q+QwIDAQABAoIBAQCmkbU68aUoldH0\n" + 
			"KwQBuGXlaenAo7ye/N3zecrmWJ8EmTpN3mCgRAfYQaO7V0X4PsecuWuMB7+J6FYL\n" + 
			"snyALQGM9WFH/oeef6+fGTGNdNX5ACoOOjzBS+pGnnmai80UU0Tb6/ZiWw0WjCOj\n" + 
			"1/a7rwFGoawP0yD8iAa4LV4sfHGKjYq6wPlql/svR8PUFFt5NVkQCP6SYMs/O8W8\n" + 
			"vVBiiI/dY2FlLb3CEi7FuGyyj7L5uVp84eFXY/FCEp3G7LHHdw9ja/ISEkEnrfHA\n" + 
			"WSZA/fWtDI/34ny5pbkl2opsAW2TZvOfBYHg/jFHvPjG+w3jfoF68p1y8EPWSfzD\n" + 
			"z2xCJ3cBAoGBAPA51sNokmBa/spXTytuO8mxeCtL9M4qf97iQFUgksfaJjQQSj3N\n" + 
			"bCTUFSXjsFhBJHYGbEYQMmAxZSMMgcFVl8PXQUpBXduOwoQ3BkCGw2Bg2TODMtpr\n" + 
			"u6aNmiQCWRXjghnqtBZhGv7HHMz6px5cj7h7lDDtkgULgprj6akSZGuNAoGBAMtv\n" + 
			"fI2QKCMcI7afrPsh7wmpByu2lp+HW0o2XBwUU6ITN8hXEoOknnxJQIcRMb94evUp\n" + 
			"UbYaK90wT608ASzjG0pz+5c23lBcMA+4gj14rfSztdkHnaNLvHeE+XZd48XTRhfo\n" + 
			"GxNt+HjdYAoB8aH6won+qHmuU0vSMjkNSFnytPUPAoGAFDF4eSKgM1NS2aVvNUJl\n" + 
			"6jVt2Y3f/TibWRwBtmYvkHcpAPVhmEJdVdcyE+ZI25JWQ0mU7mrMIUPlsYWKnrrY\n" + 
			"MBE3HsvR3PPqK/0IHIJ3h/F8MJVVwACQXJbIn0BHRszCEfWlv7/X6hTIuf5T7/xt\n" + 
			"5F8W/OnrfVsTVnvUF29BW70CgYB4nG2E5I3XWwyVbo+5CIJXAN6efQ9KH47xLc9o\n" + 
			"WmjHM4uj/HOels/bsfZO3dPXyA9mIIwwYc3VwpnKhEPSIAeDGcZ0/XPKww0/lM2q\n" + 
			"sky4Qn+y34AHW5Sua6kXE01CcSMic1ryJwXkaqNQ65wdw7To6ZFrKv55enKKPqTB\n" + 
			"p1K/4wKBgDn5jZjnPz+6iVIsKJqRG46o2t2W6TwHNrpcY+NOZf9YkaRvLk2wQBMc\n" + 
			"MlJieY2r3hyABtSBBAonRye/aCou1o6HvxN2al35I25WKBRU0iHC4SnWy0tTlSh2\n" + 
			"E7OQDt0Ni5EipeoPvQvUkwNQv0wBx7t0MHYYHSe/t3YnCmQFU1Ju\n" + 
			"-----END RSA PRIVATE KEY-----";
	
}
