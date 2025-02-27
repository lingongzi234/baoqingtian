import { defineConfig } from 'unocss'

export default defineConfig({
  content: {
    pipeline: {
      include: [
        // the default
        /\.(vue|svelte|[jt]sx|mdx?|astro|elm|php|phtml|html)($|\?)/,
        // include js/ts files
        'src/**/*.{js,ts}',
      ],
      // exclude files
      // exclude: []
    },
  },
  shortcuts: [
    {
      Input: "rounded-lg border border-solid border-gray border-3 h-6 focus:border-gray focus:outline-none"
    }
  ]
})